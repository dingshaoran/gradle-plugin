package com.android.visitor.replacedefine.name

import com.android.visitor.Constants
import com.android.visitor.factory.MethodParam
import com.android.visitor.replacedefine.DefineAdviceAdapter
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class MatchNameDefineAdviceAdapter(
    api: Int, private val internalName: String, mv: MethodVisitor, val cv: ClassVisitor, val params: MethodParam, private val config: Map<String, List<String>?>
) : DefineAdviceAdapter(api, internalName, mv, cv, params) {

    override fun getInvoke() = config[Constants.KEY_INVOKE]
}