package com.android.visitor.log

import com.android.build.api.instrumentation.ClassContext
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import java.util.*
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.ConstantDynamic
import org.objectweb.asm.Handle
import org.objectweb.asm.Label
import org.objectweb.asm.Type
import org.objectweb.asm.TypePath
import org.objectweb.asm.commons.Method
import org.objectweb.asm.commons.TableSwitchGenerator

class LogAdviceAdapter(
    api: Int, val classContext: ClassContext, mv: MethodVisitor?, access: Int, name: String, private val desc: String
) : AdviceAdapter(api, mv, access, name, desc) {


    override fun visitParameter(name: String?, access: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitParameter $name $access")
        super.visitParameter(name, access)
    }

    override fun visitAnnotationDefault(): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitAnnotationDefault")
        return super.visitAnnotationDefault()
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitAnnotation $descriptor $visible")
        return super.visitAnnotation(descriptor, visible)
    }

    override fun visitTypeAnnotation(typeRef: Int, typePath: TypePath?, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitTypeAnnotation $typeRef $typePath $descriptor $visible")
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible)
    }

    override fun visitAnnotableParameterCount(parameterCount: Int, visible: Boolean) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitAnnotableParameterCount $parameterCount $visible")
        super.visitAnnotableParameterCount(parameterCount, visible)
    }

    override fun visitParameterAnnotation(parameter: Int, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitParameterAnnotation $parameter $descriptor $visible")
        return super.visitParameterAnnotation(parameter, descriptor, visible)
    }

    override fun visitAttribute(attribute: Attribute?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitAttribute $attribute")
        super.visitAttribute(attribute)
    }

    override fun visitCode() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitCode")
        super.visitCode()
    }

    override fun visitFrame(type: Int, numLocal: Int, local: Array<out Any>?, numStack: Int, stack: Array<out Any>?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitFrame $type $numLocal ${Arrays.toString(local)} $numStack ${Arrays.toString(stack)}")
        super.visitFrame(type, numLocal, local, numStack, stack)
    }

    override fun visitInsn(opcode: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitInsn $opcode")
        super.visitInsn(opcode)
    }

    override fun visitIntInsn(opcode: Int, operand: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitIntInsn $opcode $operand")
        super.visitIntInsn(opcode, operand)
    }

    override fun visitVarInsn(opcode: Int, type: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitVarInsn $opcode $type")
        super.visitVarInsn(opcode, type)
    }

    override fun visitTypeInsn(opcode: Int, type: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitTypeInsn $opcode $type")
        super.visitTypeInsn(opcode, type)
    }

    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitFieldInsn $opcode $owner $name $descriptor")
        super.visitFieldInsn(opcode, owner, name, descriptor)
    }

    override fun visitMethodInsn(opcodeAndSource: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitMethodInsn $opcodeAndSource $owner $name $descriptor $isInterface")
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface)
    }

    override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitMethodInsn $opcode $owner $name $descriptor")
        super.visitMethodInsn(opcode, owner, name, descriptor)
    }

    override fun visitInvokeDynamicInsn(name: String?, descriptor: String?, bootstrapMethodHandle: Handle?, vararg bootstrapMethodArguments: Any?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitInvokeDynamicInsn $name $descriptor $bootstrapMethodHandle ${Arrays.toString(bootstrapMethodArguments)}")
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, *bootstrapMethodArguments)
    }

    override fun visitJumpInsn(opcode: Int, label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitJumpInsn $opcode $label")
        super.visitJumpInsn(opcode, label)
    }

    override fun visitLabel(label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLabel $label")
        super.visitLabel(label)
    }

    override fun visitLdcInsn(value: Any?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLdcInsn $value")
        super.visitLdcInsn(value)
    }

    override fun visitIincInsn(itvar: Int, increment: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitIincInsn $itvar $increment")
        super.visitIincInsn(itvar, increment)
    }

    override fun visitTableSwitchInsn(min: Int, max: Int, dflt: Label?, vararg labels: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitTableSwitchInsn $min $max $dflt ${Arrays.toString(labels)}")
        super.visitTableSwitchInsn(min, max, dflt, *labels)
    }

    override fun visitLookupSwitchInsn(dflt: Label?, keys: IntArray?, labels: Array<out Label>?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLookupSwitchInsn $dflt ${Arrays.toString(keys)} ${Arrays.toString(labels)}")
        super.visitLookupSwitchInsn(dflt, keys, labels)
    }

    override fun visitMultiANewArrayInsn(descriptor: String?, numDimensions: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitMultiANewArrayInsn $descriptor $numDimensions")
        super.visitMultiANewArrayInsn(descriptor, numDimensions)
    }

    override fun visitInsnAnnotation(typeRef: Int, typePath: TypePath?, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitInsnAnnotation $typeRef $typePath $descriptor $visible")
        return super.visitInsnAnnotation(typeRef, typePath, descriptor, visible)
    }

    override fun visitTryCatchBlock(start: Label?, end: Label?, handler: Label?, type: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitTryCatchBlock $start $end $handler $type")
        super.visitTryCatchBlock(start, end, handler, type)
    }

    override fun visitTryCatchAnnotation(typeRef: Int, typePath: TypePath?, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitTryCatchAnnotation $typeRef $typePath $descriptor $visible")
        return super.visitTryCatchAnnotation(typeRef, typePath, descriptor, visible)
    }

    override fun visitLocalVariable(name: String?, descriptor: String?, signature: String?, start: Label?, end: Label?, index: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLocalVariable $name $descriptor $signature $start $end $index")
        super.visitLocalVariable(name, descriptor, signature, start, end, index)
    }

    override fun visitLocalVariableAnnotation(typeRef: Int, typePath: TypePath?, start: Array<out Label>?, end: Array<out Label>?, index: IntArray?, descriptor: String?, visible: Boolean): AnnotationVisitor {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLocalVariableAnnotation $typeRef $typePath ${Arrays.toString(start)} ${Arrays.toString(end)} ${Arrays.toString(index)} $descriptor $visible")
        return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible)
    }

    override fun visitLineNumber(line: Int, start: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitLineNumber $line $start")
        super.visitLineNumber(line, start)
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitMaxs $maxStack $maxLocals")
        super.visitMaxs(maxStack, maxLocals)
    }

    override fun visitEnd() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter visitEnd")
        super.visitEnd()
    }

    override fun newLocal(type: Type?): Int {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter newLocal $type")
        return super.newLocal(type)
    }

    override fun updateNewLocals(newLocals: Array<out Any>?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter updateNewLocals $newLocals")
        super.updateNewLocals(newLocals)
    }

    override fun setLocalType(local: Int, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter setLocalType $local $type")
        super.setLocalType(local, type)
    }

    override fun newLocalMapping(type: Type?): Int {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter newLocalMapping $type")
        return super.newLocalMapping(type)
    }

    override fun getAccess(): Int {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getAccess")
        return super.getAccess()
    }

    override fun getName(): String {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getName")
        return super.getName()
    }

    override fun getReturnType(): Type {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getReturnType")
        return super.getReturnType()
    }

    override fun getArgumentTypes(): Array<Type> {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getArgumentTypes")
        return super.getArgumentTypes()
    }

    override fun push(value: Boolean) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push z $value")
        super.push(value)
    }

    override fun push(value: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push i $value")
        super.push(value)
    }

    override fun push(value: Long) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push j $value")
        super.push(value)
    }

    override fun push(value: Float) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push f $value")
        super.push(value)
    }

    override fun push(value: Double) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push d $value")
        super.push(value)
    }

    override fun push(value: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push a  $value")
        super.push(value)
    }

    override fun push(value: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push $value")
        super.push(value)
    }

    override fun push(handle: Handle?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push $handle")
        super.push(handle)
    }

    override fun push(constantDynamic: ConstantDynamic?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter push $constantDynamic")
        super.push(constantDynamic)
    }

    override fun loadThis() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadThis")
        super.loadThis()
    }

    override fun loadArg(arg: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadArg $arg")
        super.loadArg(arg)
    }

    override fun loadArgs(arg: Int, count: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadArgs $arg $count")
        super.loadArgs(arg, count)
    }

    override fun loadArgs() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadArgs")
        super.loadArgs()
    }

    override fun loadArgArray() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadArgArray")
        super.loadArgArray()
    }

    override fun storeArg(arg: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter storeArg $arg")
        super.storeArg(arg)
    }

    override fun getLocalType(local: Int): Type {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getLocalType $local")
        return super.getLocalType(local)
    }

    override fun loadLocal(local: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadLocal $local")
        super.loadLocal(local)
    }

    override fun loadLocal(local: Int, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter loadLocal $local $type")
        super.loadLocal(local, type)
    }

    override fun storeLocal(local: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter storeLocal $local")
        super.storeLocal(local)
    }

    override fun storeLocal(local: Int, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter storeLocal $local $type")
        super.storeLocal(local, type)
    }

    override fun arrayLoad(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter arrayLoad $type")
        super.arrayLoad(type)
    }

    override fun arrayStore(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter arrayStore $type")
        super.arrayStore(type)
    }

    override fun pop() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter pop")
        super.pop()
    }

    override fun pop2() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter pop2")
        super.pop2()
    }

    override fun dup() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dup")
        super.dup()
    }

    override fun dup2() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dup2")
        super.dup2()
    }

    override fun dupX1() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dupX1")
        super.dupX1()
    }

    override fun dupX2() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dupX2")
        super.dupX2()
    }

    override fun dup2X1() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dup2X1")
        super.dup2X1()
    }

    override fun dup2X2() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter dup2X2")
        super.dup2X2()
    }

    override fun swap() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter swap")
        super.swap()
    }

    override fun swap(prev: Type?, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter swap $prev $type")
        super.swap(prev, type)
    }

    override fun math(op: Int, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter math $op $type")
        super.math(op, type)
    }

    override fun not() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter not")
        super.not()
    }

    override fun iinc(local: Int, amount: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter iinc $local $amount")
        super.iinc(local, amount)
    }

    override fun cast(from: Type?, to: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter cast $from $to")
        super.cast(from, to)
    }

    override fun box(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter box $type")
        super.box(type)
    }

    override fun valueOf(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter valueOf $type")
        super.valueOf(type)
    }

    override fun unbox(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter unbox $type")
        super.unbox(type)
    }

    override fun newLabel(): Label {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter newLabel")
        return super.newLabel()
    }

    override fun mark(label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter mark $label")
        super.mark(label)
    }

    override fun mark(): Label {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter mark")
        return super.mark()
    }

    override fun ifCmp(type: Type?, mode: Int, label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ifCmp $type $mode $label")
        super.ifCmp(type, mode, label)
    }

    override fun ifICmp(mode: Int, label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ifICmp $mode $label")
        super.ifICmp(mode, label)
    }

    override fun ifZCmp(mode: Int, label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ifZCmp $mode $label")
        super.ifZCmp(mode, label)
    }

    override fun ifNull(label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ifNull $label")
        super.ifNull(label)
    }

    override fun ifNonNull(label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ifNonNull $label")
        super.ifNonNull(label)
    }

    override fun goTo(label: Label?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter goTo $label")
        super.goTo(label)
    }

    override fun ret(local: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter ret $local")
        super.ret(local)
    }

    override fun tableSwitch(keys: IntArray?, generator: TableSwitchGenerator?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter tableSwitch ${Arrays.toString(keys)} $generator")
        super.tableSwitch(keys, generator)
    }

    override fun tableSwitch(keys: IntArray?, generator: TableSwitchGenerator?, useTable: Boolean) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter tableSwitch ${Arrays.toString(keys)} $generator $useTable")
        super.tableSwitch(keys, generator, useTable)
    }

    override fun returnValue() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter returnValue")
        super.returnValue()
    }

    override fun getStatic(owner: Type?, name: String?, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getStatic $owner $name $type")
        super.getStatic(owner, name, type)
    }

    override fun putStatic(owner: Type?, name: String?, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter putStatic $owner $name $type")
        super.putStatic(owner, name, type)
    }

    override fun getField(owner: Type?, name: String?, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter getField $owner $name $type")
        super.getField(owner, name, type)
    }

    override fun putField(owner: Type?, name: String?, type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter putField $owner $name $type")
        super.putField(owner, name, type)
    }


    override fun invokeVirtual(owner: Type?, method: Method?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter invokeVirtual $owner $method")
        super.invokeVirtual(owner, method)
    }

    override fun invokeConstructor(type: Type?, method: Method?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter invokeConstructor $type $method")
        super.invokeConstructor(type, method)
    }

    override fun invokeStatic(owner: Type?, method: Method?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter invokeStatic $owner $method")
        super.invokeStatic(owner, method)
    }

    override fun invokeInterface(owner: Type?, method: Method?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter invokeInterface $owner $method")
        super.invokeInterface(owner, method)
    }

    override fun invokeDynamic(name: String?, descriptor: String?, bootstrapMethodHandle: Handle?, vararg bootstrapMethodArguments: Any?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter invokeDynamic $name $descriptor $bootstrapMethodHandle ${Arrays.toString(bootstrapMethodArguments)}")
        super.invokeDynamic(name, descriptor, bootstrapMethodHandle, *bootstrapMethodArguments)
    }


    override fun newInstance(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter newInstance $type ")
        super.newInstance(type)
    }

    override fun newArray(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter newArray $type")
        super.newArray(type)
    }

    override fun arrayLength() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter arrayLength")
        super.arrayLength()
    }

    override fun throwException() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter throwException")
        super.throwException()
    }

    override fun throwException(type: Type?, message: String?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter throwException $type $message")
        super.throwException(type, message)
    }

    override fun checkCast(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter checkCast $type")
        super.checkCast(type)
    }

    override fun instanceOf(type: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter instanceOf $type")
        super.instanceOf(type)
    }

    override fun monitorEnter() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter monitorEnter")
        super.monitorEnter()
    }

    override fun monitorExit() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter monitorExit")
        super.monitorExit()
    }

    override fun endMethod() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter endMethod")
        super.endMethod()
    }

    override fun catchException(start: Label?, end: Label?, exception: Type?) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter catchException $start $end $exception")
        super.catchException(start, end, exception)
    }

    override fun onMethodEnter() {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter onMethodEnter")
        super.onMethodEnter()
    }

    override fun onMethodExit(opcode: Int) {
        Logger.getGlobal().log(LEVEL, "LogAdviceAdapter onMethodExit $opcode")
        super.onMethodExit(opcode)
    }

    companion object {
        private val LEVEL = Level.WARNING
    }
}