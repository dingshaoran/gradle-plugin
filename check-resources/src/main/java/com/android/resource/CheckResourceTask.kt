package com.android.resource


import CheckResourceExtension
import com.android.build.gradle.internal.LoggerWrapper
import com.android.build.gradle.tasks.MergeResources
import com.android.ide.common.resources.ResourceMerger
import com.android.ide.common.resources.ResourceMergerItem
import java.math.BigInteger
import java.security.MessageDigest
import java.util.logging.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class CheckResourceTask : DefaultTask() {

    companion object {
        @JvmStatic
        fun register(project: Project, params: CheckResourceExtension) {
            project.tasks.filterNot { it.name.contains("AndroidTest") }.filterIsInstance<MergeResources>().forEach { mergeResource ->
                val tasks = project.tasks.matching { item ->
                    item.name.startsWith("package${mergeResource.variantName}", true)
                }.filterNot { it.name.contains("AndroidTest") }
                if (tasks.isNotEmpty()) {
                    project.tasks.register("check${mergeResource.variantName}DuplicateRes", CheckResourceTask::class.java) { cdr ->
                        cdr.app.set(params.app)
                        cdr.module.set(params.module)
                        cdr.maven.set(params.maven)
                        cdr.dependsOn(mergeResource)
                        tasks.forEach { item -> item.dependsOn(cdr) }
                    }

                }
            }
        }
    }


    @get:Input
    abstract val app: Property<Boolean>

    @get:Input
    abstract val module: Property<Boolean>

    @get:Input
    abstract val maven: Property<Boolean>

    @ExperimentalStdlibApi
    @TaskAction
    fun taskAction() {
        val merger = ResourceMerger(19)
        dependsOn.forEach {
            val mergeResourcesTask = it as MergeResources
            mergeResourcesTask.resourcesComputer.compute(false, mergeResourcesTask.aaptEnv.orNull, mergeResourcesTask.renderscriptGeneratedResDir).forEach { resourceSet ->
                resourceSet.loadFromFiles(LoggerWrapper(it.getLogger()))
                if (!merger.dataSets.contains(resourceSet)) merger.addDataSet(resourceSet)
            }
        }

        /**
         * 保存哪些 path 是主 app 包含的 path，这些 path 优先级高，即使重复资源内容不一致也使用主 app 下的资源
         * {path:<true,androidx.lifecycle>}
         */
        val fileConfigName = HashMap<String, Pair<Boolean, String>>()
        merger.dataSets.forEach { resourceSet ->
            resourceSet.sourceFiles.forEach { f ->
                fileConfigName.put(f.toString(), Pair(!resourceSet.isFromDependency, resourceSet.configName))
            }
        }
        val tmp = StringBuilder()
        val log = LogPrinter()
        val cache = HashMap<String, ResourceMergerItem>()
        val regex = Regex("\\s")//替换字符串中不可见字符，比如空格换行之类的
        merger.dataMap.asMap().forEach { entry ->
            /**
             * entry 的结构为 {string/app_name:[appName1,appName1,appName2]} 只要判断出来是不是有高优先级的 model 就可以，如果优先级相同则会出现不确定性直接打包报错
             * 把 value 作为 key 放到 cache 里，如果 value 有差异则 cache.size > 1
             * styleable、attr 会被 merge
             */
            if (entry.value.size > 1 && !entry.key.startsWith("styleable/") && !entry.key.startsWith("attr/")) {
                cache.clear()
                entry.value.forEach { item ->
                    if (item.value != null) {//string
                        tmp.setLength(0)
                        printNode(tmp, item.value)
                        cache.put(tmp.toString().replace(regex, ""), item)
                    } else if(item.file.exists()){
                        cache.put(getMd5Digest(item.file.readBytes()), item)
                    }
                }
                if (cache.size > 1) {//有多个 value 不一致的 item 了，打印日志
                    createLog(cache, fileConfigName, log)
                }
            }
        }
        printLog(log.containApp, app)
        printLog(log.containModule, module)
        printLog(log.allMaven, maven)
    }

    private fun printLog(log: StringBuilder, isShow: Property<Boolean>) {
        if (log.isNotEmpty()) {
            if (isShow.getOrElse(false)) {
                throw RuntimeException("请检查并修改重复资源为相同值，重复资源如下\n$log")
            } else {
                Logger.getGlobal().warning("CheckDuplicateRes\n$log")
            }
        }
    }

    /**
     * 有重复的资源，把 log 都记录到 StringBuilder 里
     */
    private fun createLog(cache: HashMap<String, ResourceMergerItem>, fileConfigName: HashMap<String, Pair<Boolean, String>>, log: LogPrinter) {
        var containAppModule = false //app module的资源不参与重复排查
        var containLibModule = false//包含其他 module
        val temp = StringBuilder()
        cache.forEach { (key, item) ->
            var cacheConfigName = item.file.toString()
            for (fe in fileConfigName) {
                if (cacheConfigName.startsWith(fe.key)) {
                    if (fe.value.first) {
                        containAppModule = true
                    } else {
                        cacheConfigName = fe.value.second
                        containLibModule = cacheConfigName.startsWith(":")//lib 的引入方式:library
                    }
                    //string/app_name,AppName1,androidx.lifecycle
                    temp.append(item.key?.replace(",", "，")).append(",\t").append(key).append(",\t").append(cacheConfigName?.replace(",", "，")).append("\n")
//                    break 把所有不一致的文件都打印出来，方便业务同学排查修改
                }
            }
        }
        if (containAppModule) {
            if (temp.isNotEmpty()) log.containApp.append(temp).append("\n")
        } else if (containLibModule) {
            if (temp.isNotEmpty()) log.containModule.append(temp).append("\n")
        } else {
            if (temp.isNotEmpty()) log.allMaven.append(temp).append("\n")
        }
    }

    /**
     * 把 xml 中写的字符串资源转为纯 String 方便对比差异
     */
    private fun printNode(stringBuilder: StringBuilder, node: org.w3c.dom.Node) {
        val type = node.getNodeType();
        val isClosed = node.getFirstChild() != null || "string" == node.getNodeName()
        if (org.w3c.dom.Node.TEXT_NODE == type) {
            stringBuilder.append(node.getNodeValue().trim())
        } else if (org.w3c.dom.Node.ELEMENT_NODE == type) {
            stringBuilder.append('<').append(node.getNodeName().replaceFirst(".*:", ""))
            //防止命名空间的取名差异导致 diff
            val attributes = node.getAttributes()
            val attributeCount = attributes.getLength();
            for (i in 0 until attributeCount) {
                val attribute = attributes.item(i)
                val name = attribute.nodeName.replaceFirst(".*:", "")
                if (name != "translatable") {//translatable 不作为是否一致的依据
                    stringBuilder.append(" ").append(name).append('=').append(attribute.nodeValue)
                }
            }
            if (!isClosed) {
                stringBuilder.append('/');
            }
            stringBuilder.append('>');
        }
        val children = node.getChildNodes();
        val n = children.getLength()
        for (i in 0 until n) {
            val child = children.item(i);
            printNode(stringBuilder, child);
        }
        if (org.w3c.dom.Node.ELEMENT_NODE == type) {
            if (isClosed) {
                stringBuilder.append("</").append(node.getNodeName().replaceFirst(".*:", "")).append(">")
            }
        }
    }

    private fun getMd5Digest(pInput: ByteArray): String {
        val lDigest: MessageDigest = MessageDigest.getInstance("MD5")
        lDigest.update(pInput)
        val lHashInt = BigInteger(1, lDigest.digest())
        return java.lang.String.format("%1$032X", lHashInt)
    }

    private class LogPrinter(
        val containApp: StringBuilder = StringBuilder(),//重复的资源中 app 有定义，不报错
        val containModule: StringBuilder = StringBuilder(),//重复的资源中 module 有定义，报错
        val allMaven: StringBuilder = StringBuilder(),//重复的资源中都是 maven 依赖，不报错
    )
}
