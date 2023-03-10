package com.android.visitor.factory

import com.android.build.api.instrumentation.ClassContext
import com.android.visitor.Constants
import com.android.visitor.log.LogAdviceAdapter
import java.util.Arrays
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.ModuleVisitor
import org.objectweb.asm.RecordComponentVisitor
import org.objectweb.asm.TypePath

class LogClassVisitor(val classContext: ClassContext, nextClassVisitor: ClassVisitor) : ClassVisitor(Constants.API_VER, nextClassVisitor) {

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visit $version $access $name $signature $superName ${Arrays.toString(interfaces)}")
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitSource(source: String?, debug: String?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitSource $source $debug ")
        super.visitSource(source, debug)
    }

    override fun visitModule(name: String?, access: Int, version: String?): ModuleVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitModule $name $access $version")
        return super.visitModule(name, access, version)
    }

    override fun visitNestHost(nestHost: String?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitNestHost $nestHost")
        super.visitNestHost(nestHost)
    }

    override fun visitOuterClass(owner: String?, name: String?, descriptor: String?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitOuterClass $owner $name $descriptor")
        super.visitOuterClass(owner, name, descriptor)
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitAnnotation $descriptor $visible")
        return super.visitAnnotation(descriptor, visible)
    }

    override fun visitTypeAnnotation(typeRef: Int, typePath: TypePath?, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitTypeAnnotation $typeRef $typePath $descriptor $visible")
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible)
    }

    override fun visitAttribute(attribute: Attribute?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitAttribute $attribute")
        super.visitAttribute(attribute)
    }

    override fun visitNestMember(nestMember: String?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitNestMember $nestMember")
        super.visitNestMember(nestMember)
    }

    override fun visitPermittedSubclass(permittedSubclass: String?) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitPermittedSubclass $permittedSubclass")
        super.visitPermittedSubclass(permittedSubclass)
    }

    override fun visitInnerClass(name: String?, outerName: String?, innerName: String?, access: Int) {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitInnerClass $name $outerName $innerName $access")
        super.visitInnerClass(name, outerName, innerName, access)
    }

    override fun visitRecordComponent(name: String?, descriptor: String?, signature: String?): RecordComponentVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitRecordComponent $name $descriptor $signature")
        return super.visitRecordComponent(name, descriptor, signature)
    }

    override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitField $access $name $descriptor $signature $value")
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitMethod(access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitMethod $access $name $descriptor $signature ${Arrays.toString(exceptions)}")
//        return super.visitMethod(access, name, descriptor, signature, exceptions)
        return LogAdviceAdapter(Constants.API_VER, classContext, super.visitMethod(access, name, descriptor, signature, exceptions), access, name, descriptor)
    }

    override fun visitEnd() {
        Logger.getGlobal().log(LEVEL, "LogClassVisitor visitEnd")
        super.visitEnd()
    }

    companion object {
        private val LEVEL = Level.WARNING
    }
}