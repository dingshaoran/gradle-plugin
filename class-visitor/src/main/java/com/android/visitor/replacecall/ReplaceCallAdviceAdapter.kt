package com.android.visitor.replacecall

import com.android.build.api.instrumentation.ClassContext
import com.android.visitor.Constants
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import com.android.visitor.TypeUtils
import com.android.visitor.factory.MethodParam

class ReplaceCallAdviceAdapter(
    api: Int,
    private val internalName: String,
    mv: MethodVisitor?,
    params: MethodParam,
    private val config: Map<String, Map<String, Map<String, List<String>?>?>?>
) : AdviceAdapter(api, mv, params.access, params.name, params.descriptor) {

    override fun visitMethodInsn(opcode: Int, owner: String, name: String, descriptor: String, isInterface: Boolean) {
        val configMethod = config.get(owner)?.get(name + descriptor)
        if (configMethod != null) {
            val invoke = configMethod.get(Constants.KEY_INVOKE)
            if (invoke != null && invoke.get(0) != internalName && TypeUtils.match(configMethod, internalName)) {
                val invokeDesc = TypeUtils.getInvokeDescriptor(descriptor, invoke.getOrNull(2)?:owner, INVOKESTATIC == opcode)
                Logger.getGlobal().log(Level.WARNING, "visitor.ReplaceCallAdviceAdapter $internalName.${this.name} call $owner.$name$descriptor replace ${invoke[0]}.${invoke.get(1)}$invokeDesc")
                mv.visitMethodInsn(INVOKESTATIC, invoke.get(0), invoke.get(1), invokeDesc, false)
                return
            }
        }
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
    }
}