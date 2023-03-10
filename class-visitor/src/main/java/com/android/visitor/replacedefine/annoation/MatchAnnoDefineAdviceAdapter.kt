package com.android.visitor.replacedefine.annoation

import com.android.visitor.Constants
import com.android.visitor.TypeUtils
import com.android.visitor.factory.MethodParam
import com.android.visitor.replacedefine.DefineAdviceAdapter
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import org.objectweb.asm.Handle
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

class MatchAnnoDefineAdviceAdapter(
    api: Int, private val internalName: String, mv: MethodVisitor, val cv: ClassVisitor, val params: MethodParam, private val config: Map<String, Map<String, List<String>?>?>?
) : DefineAdviceAdapter(api, internalName, mv, cv, params) {

    private var isMatchAnno: List<String>? = null

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        if (isMatchAnno == null) {
            config?.get(descriptor)?.takeIf { TypeUtils.match(it, internalName) }?.let {
                isMatchAnno = it.get(Constants.KEY_INVOKE)
                Logger.getGlobal().log(Level.WARNING, "visitor.MatchAnnoDefineAdviceAdapter $internalName.${params.name} match $descriptor")
            }
        }
        return super.visitAnnotation(descriptor, visible)
    }

    override fun getInvoke() = isMatchAnno
}