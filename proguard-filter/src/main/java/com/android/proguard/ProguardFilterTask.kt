package com.android.proguard


import com.android.build.gradle.internal.tasks.ProguardConfigurableTask
import java.io.File
import java.net.URL
import java.util.logging.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


abstract class ProguardFilterTask : DefaultTask() {
    private val dictProguard = setOf(
        "-obfuscationdictionary", "-classobfuscationdictionary", "-packageobfuscationdictionary", "-printconfiguration", "-printseeds", "-include", "-injars", "-outjars", "-libraryjars", "-keepdirectories", "-printusage", "-printmapping", "-applymapping", "-dump"
    )

    companion object {
        const val BASEDIRECTORY = "-basedirectory"

        @JvmStatic
        fun register(project: Project, params: Map<String, String?>) {
            project.tasks.filterNot { it.name.contains("AndroidTest") }.filterIsInstance<ProguardConfigurableTask>().forEach { pct ->
                val fileCollect = pct.dependsOn.toList()
                pct.setDependsOn(
                    listOf(project.tasks.register("filter${pct.variantName}ProguardFile", ProguardFilterTask::class.java) { pft ->
                        pft.config.set(params)
                        pft.proguardTaskName.set(pct.name)
                        pft.setDependsOn(fileCollect)
                    })
                )
            }
        }
    }

    @get:Input
    abstract val config: MapProperty<String, String?>

    @get:Input
    abstract val proguardTaskName: Property<String>


    @ExperimentalStdlibApi
    @TaskAction
    fun taskAction() {
        val files = (project.tasks.getByName(proguardTaskName.get()) as ProguardConfigurableTask).configurationFiles
        val transformProguard = HashSet<File>()
        val dirProguard = File(project.buildDir, "outputs" + File.separator + "proguard")
        files.filter { it.exists() }.forEach {
            reachFile(dirProguard, it, transformProguard, config.get())
        }
        files.setFrom(transformProguard)
    }

    private fun reachFile(target: File, it: File, transformProguard: HashSet<File>, params: Map<String, String?>) {
        if (it.isDirectory) {
            it.listFiles()?.let { subFiles ->
                for (item in subFiles) {
                    reachFile(target, item, transformProguard, params)
                }
            }
        } else {
            target.mkdirs()
            val targetProguard = File(target, it.absolutePath.hashCode().toString(36) + ".txt")
            mapProguardFile(it, targetProguard, params)
            transformProguard.add(targetProguard)
        }
    }

    private fun mapProguardFile(from: File, to: File, params: Map<String, String?>) {
        Logger.getGlobal().warning("ProguardFilter $from $to")
        to.writeText("# ${from.absolutePath}\n")
        var proguardTxt = replacePath((from.readText(Charsets.UTF_8).noSpace), from)
        for (entry in params) {
            proguardTxt = proguardTxt.replace(entry.key.noSpace, entry.value ?: "")
        }
        to.appendText(proguardTxt, Charsets.UTF_8)

    }

    /**
     *  多个换行空格替换为一个，防止配的 string 因为空格不匹配
     */
    private val String.noSpace: String
        get() = this.replace("\r\n", "\n").replace("\t", " ").replace(Regex(" +"), " ").replace(Regex(" *\n"), "\n")

    /**
     * 相对路径替换为绝对路径
     */
    private fun replacePath(data: String, input: File): String {
        var basePath = input.toURI().toURL()
        val arr = data.split("\n").toMutableList()
        for (s in arr) {
            if (s.startsWith(BASEDIRECTORY)) {
                basePath = File(getAttribute(BASEDIRECTORY, s).first).toURI().toURL()
                break
            }
        }

        for (i in arr.indices) {
            val line = arr[i]
            if (line.startsWith("#")) {
                continue
            }
            val firstBlank = line.indexOf(' ')
            if (firstBlank <= 0) {
                continue
            }
            val key = line.substring(0, firstBlank)
            if (dictProguard.contains(key)) {
                val attribute = getAttribute(key, line)
                if (attribute.first.startsWith(".")) {//相对路径
                    arr[i] = key + " " + URL(basePath, attribute.first).file + " " + attribute.second;
                }
            }
        }
        return arr.joinToString("\n")
    }

    private fun getAttribute(key: String, line: String): Pair<String, String> {
        val commentIndex = line.indexOf('#');
        if (commentIndex > 0) {
            return Pair(line.substring(key.length, commentIndex).trim(), line.substring(commentIndex))
        } else {
            return Pair(line.substring(key.length).trim(), "")
        }
    }
}
