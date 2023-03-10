package com.android.visitor.riskcheck

import com.android.build.api.instrumentation.ClassContext
import com.android.visitor.Constants
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import com.android.visitor.TypeUtils
import com.android.visitor.factory.CopyFramesAllVisitor
import com.android.visitor.factory.MethodParam

class RiskCheckAdviceAdapter(
    api: Int,
    private val internalName: String,
    mv: MethodVisitor?,
    params: MethodParam,
    private val config: Map<String, Map<String, Map<String, List<String>?>?>?>
) : AdviceAdapter(api, mv, params.access, params.name, params.descriptor) {


    override fun visitLdcInsn(value: Any?) {
        super.visitLdcInsn(value)
        val stringConfig = config.get(Constants.KEY_TYPE_STRING)?.get(value)
        if (stringConfig != null && TypeUtils.match(stringConfig, internalName)) {
            throw RiskError("visitor.riskcheck.RiskError visit $internalName.$name use String $value")
        }
    }

    override fun visitTypeInsn(opcode: Int, type: String?) {
        super.visitTypeInsn(opcode, type)
        val newConfig = config.get(Constants.KEY_TYPE_NEW)?.get(type)
        if (opcode == NEW && newConfig != null && TypeUtils.match(newConfig, internalName)) {
            throw RiskError("visitor.riskcheck.RiskError visit $internalName.$name new Class $type")
        }
    }
}
