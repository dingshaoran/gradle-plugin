package com.android.visitor.replacestring

import com.android.visitor.Constants
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import com.android.visitor.TypeUtils
import com.android.visitor.factory.MethodParam
import com.android.visitor.riskcheck.RiskError

class ReplaceStringAdviceAdapter(
    api: Int, private val internalName: String, mv: MethodVisitor?, private val params: MethodParam, private val config: Map<String, Map<String, Any>?>
) : AdviceAdapter(api, mv, params.access, params.name, params.descriptor) {

    override fun visitLdcInsn(value: Any?) {
        val stringConfig = config.get(value)
        if (stringConfig != null && TypeUtils.match(stringConfig, internalName)) {

            val string = stringConfig.get(Constants.KEY_TYPE_STRING)//配置了直接替换的字符串
            val invoke = stringConfig.get(Constants.KEY_INVOKE) as? List<String> //配置了要替换的方法
            if (string != null) {
                if (string is String) {
                    super.visitLdcInsn(string)
                } else if (string is List<*>) {
                    super.visitLdcInsn(string.get(0))
                }
            } else if (invoke != null) {
                val isStatic = params.access and ACC_STATIC != 0//true 静态

                val invokeDesc = TypeUtils.getInvokeDescriptor("()Ljava/lang/String;", invoke.getOrNull(2) ?: internalName, isStatic)
                Logger.getGlobal().log(Level.WARNING, "visitor.ReplaceStringAdviceAdapter $internalName.${this.name} string:$value replace ${invoke[0]}.${invoke.get(1)}$invokeDesc")
                if (!isStatic) {
                    loadThis()
                }
//                super.visitLdcInsn(value) googlePlay 等扫描 apk 中出现字符串会审核失败，直接替换掉 apk 中的字符串，不通过参数传入
                mv.visitMethodInsn(INVOKESTATIC, invoke.get(0), invoke.get(1), invokeDesc, false)
            }
        } else {
            super.visitLdcInsn(value)
        }
    }
}