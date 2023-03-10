package com.android.visitor.factory

import com.android.build.api.instrumentation.ClassContext
import com.android.visitor.Constants
import com.android.visitor.TypeUtils
import com.android.visitor.replacecall.ReplaceCallAdviceAdapter
import com.android.visitor.replacedefine.annoation.MatchAnnoDefineAdviceAdapter
import com.android.visitor.replacedefine.name.MatchNameDefineAdviceAdapter
import com.android.visitor.replacestring.ReplaceStringAdviceAdapter
import com.android.visitor.riskcheck.RiskCheckAdviceAdapter
import com.android.visitor.riskcheck.RiskError
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor

class CopyFramesAllVisitor(val classContext: ClassContext, nextClassVisitor: ClassVisitor, private val parameters: Map<String, Map<String, Any?>?>?) : ClassVisitor(Constants.API_VER, nextClassVisitor) {
    private lateinit var internalName: String
    private var riskCheck: Map<String, Map<String, Map<String, List<String>?>?>?>? = null
    private var replaceCall: Map<String, Map<String, Map<String, List<String>?>?>?>? = null
    private var replaceDefineAnno: Map<String, Map<String, List<String>?>?>? = null
    private var replaceDefineName: Map<String, Map<String, List<String>?>?>? = null
    private var replaceString: Map<String, Map<String, Any>?>? = null
    override fun visit(version: Int, access: Int, name: String, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        internalName = name
        riskCheck = parameters?.get(Constants.KEY_RISKCHECK) as Map<String, Map<String, Map<String, List<String>?>?>?>?
        replaceCall = parameters?.get(Constants.KEY_REPLACECALL) as Map<String, Map<String, Map<String, List<String>?>?>?>?
        replaceDefineAnno = parameters?.get(Constants.KEY_ANNOTATION) as Map<String, Map<String, List<String>?>?>?
        replaceDefineName = (parameters?.get(Constants.KEY_REPLACEDEFINE) as Map<String, Map<String, Map<String, List<String>?>?>?>?)?.let { it.get(name) ?: it.get(superName) ?: interfaces?.firstNotNullOfOrNull { ift -> it.get(ift) } }
        replaceString = parameters?.get(Constants.KEY_REPLACESTRING) as Map<String, Map<String, Any>?>?
        Logger.getGlobal().log(Level.INFO, "visitor.CopyFramesAllVisitor $internalName ${replaceDefineName != null}")

        riskCheck?.get(Constants.KEY_TYPE_EXTENDS)?.let { superClazz ->
            val nameConfig = superClazz.get(superName)
            if (interfaces?.any {
                    val interfaceConfig = superClazz.get(it)
                    interfaceConfig != null && TypeUtils.match(interfaceConfig, name)
                } == true || (nameConfig != null && TypeUtils.match(nameConfig, name))) {
                throw RiskError("visitor.${Constants.KEY_RISKCHECK}.RiskError visit $name ${Constants.KEY_TYPE_EXTENDS} $superName")
            }
        }
    }

    override fun visitMethod(access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        //替换方法定义
        val params = MethodParam(access, name, descriptor, signature, exceptions) //AdviceAdapter 可能替换里面的值
        val defineMethod = replaceDefineName?.get(name + descriptor)
        var methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (defineMethod != null && TypeUtils.match(defineMethod, internalName)) {
            methodVisitor = MatchNameDefineAdviceAdapter(Constants.API_VER, internalName, methodVisitor, cv, params, defineMethod)
        } else {
            methodVisitor = MatchAnnoDefineAdviceAdapter(Constants.API_VER, internalName, methodVisitor, cv, params, replaceDefineAnno)
        }

        //检查方法内的字符串
        riskCheck?.let {
            methodVisitor = RiskCheckAdviceAdapter(Constants.API_VER, internalName, methodVisitor, params, it)
        }

        //替换方法调用
        replaceCall?.let {
            methodVisitor = ReplaceCallAdviceAdapter(Constants.API_VER, internalName, methodVisitor, params, it)
        }

        //替换方法调用
        replaceString?.let {
            methodVisitor = ReplaceStringAdviceAdapter(Constants.API_VER, internalName, methodVisitor, params, it)
        }
//        写一个 java 文件，编译的时候输出调用 asm 日志，方便照葫芦画瓢
//        if (originName == "demo1") return LogAdviceAdapter(Constants.API_VER, classContext, methodVisitor, transformAccess, transformName, descriptor)
        return methodVisitor
    }
}

data class MethodParam(var access: Int, var name: String, val descriptor: String, val signature: String?, val exceptions: Array<out String>?)
