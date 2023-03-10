package com.android.visitor.replacedefine

import com.android.visitor.TypeUtils
import com.android.visitor.factory.MethodParam
import java.util.logging.Level
import java.util.logging.Logger
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter
import org.objectweb.asm.Handle
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

abstract class DefineAdviceAdapter(
    api: Int, private val internalName: String, mv: MethodVisitor, private val cv: ClassVisitor, private val params: MethodParam
) : AdviceAdapter(api, mv, params.access, params.name, params.descriptor) {
    val transformName = "_asm_around_${params.name}"  //原有方法名修改为asm_around_开头
    val lambdaName = "_asm_around_${params.name}Lambda"//为 lambda表达式调用生成的新方法，返回值是 Object
    val lambdaDescriptor = params.descriptor.split(")")[0] + ")Ljava/lang/Object;"
    abstract fun getInvoke(): List<String>?

    private val transformAccess = (params.access and (Opcodes.ACC_PUBLIC or Opcodes.ACC_PROTECTED).inv()) or Opcodes.ACC_PRIVATE

    override fun visitCode() {
        val invoke = getInvoke()
        if (invoke == null) {
            super.visitCode()
        } else {
            mv.visitCode()
            visitLambdaInvoke(invoke)//有序调用
            params.access = transformAccess
            params.name = lambdaName
            mv = cv.visitMethod(access, name, lambdaDescriptor, params.signature, params.exceptions)//添加 lambda 调用方法实现
            mv.visitCode()
            visitLambdaDefine()
            params.name = transformName
            mv = cv.visitMethod(access, name, params.descriptor, params.signature, params.exceptions)//定义新方法名
            super.visitCode()//插入原有方法内容
        }
    }

    /**
     * 原有方法替换为 配置方法
     */
    private fun visitLambdaInvoke(invoke: List<String>) {
        val isStatic = params.access and ACC_STATIC != 0//true 静态
        val interfaceMethodName = "call"
        val interfaceClassName = "Ljava/util/concurrent/Callable;"
        val returnName = returnType.descriptor

        val invokeDesc = TypeUtils.getInvokeDescriptor(params.descriptor, invoke.getOrNull(2) ?: internalName, isStatic, interfaceClassName)
        Logger.getGlobal().log(Level.WARNING, "visitor.ReplaceDefineAdviceAdapter $internalName.${params.name} $returnName replace ${invoke[0]}.${invoke.get(1)}$invokeDesc")

        var createLambdaDesc = params.descriptor //从 descriptor 变换为 lambda 的构造方法  构造方法参数)接口名
        if (!isStatic) {
            createLambdaDesc = createLambdaDesc.replace("(", "(L$internalName;")//非静态方法第一个参数为 this
        }
        createLambdaDesc = createLambdaDesc.split(")")[0] + ")$interfaceClassName" //替换返回值为 接口欧名
        val lambdaFactory = Handle(
            H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles\$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", false
        )//创建一个 lambda 固定写法
        val interfaceDef = Handle(if (isStatic) H_INVOKESTATIC else H_INVOKESPECIAL, internalName, lambdaName, lambdaDescriptor, false)//接口实现调用外面的_round_asm_methodName

        if (!isStatic) {
            loadThis()
        }
        loadArgs()
        mv.visitInvokeDynamicInsn(interfaceMethodName, createLambdaDesc, lambdaFactory, Type.getType("()Ljava/lang/Object;"), interfaceDef, Type.getType("()Ljava/lang/Object;"))

        if (!isStatic) {//静态方法
            loadThis()
        }
        loadArgs()
        mv.visitMethodInsn(INVOKESTATIC, invoke.get(0), invoke.get(1), invokeDesc, false)
        mv.visitInsn(returnType.getOpcode(IRETURN))
        mv.visitMaxs(getArgStack() + returnType.size, argumentTypes.size + 2)
        mv.visitEnd()
    }

    /**
     * 新增定义 lambda 调用方法，返回 Object
     */
    private fun visitLambdaDefine() {
        val isStatic = access and ACC_STATIC != 0
        if (!isStatic) {
            loadThis()
        }
        loadArgs()
        mv.visitMethodInsn(if (isStatic) INVOKESTATIC else INVOKESPECIAL, internalName, transformName, params.descriptor, false)
        when (Type.getReturnType(params.descriptor).getOpcode(IRETURN)) {
            IRETURN -> {
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            }
            LRETURN -> {
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            }
            FRETURN -> {
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
            }
            DRETURN -> {
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
            }
            RETURN -> {
                mv.visitInsn(ACONST_NULL)
            }
            else -> {//ARETURN 或者其他操作码
            }
        }
        mv.visitInsn(ARETURN)
        mv.visitMaxs(getArgStack(), argumentTypes.size + 1)
        mv.visitEnd()
    }

    private fun getArgStack(): Int {
        var index = if (params.access and ACC_STATIC == 0) 1 else 0
        for (element in argumentTypes) {
            index += element.size
        }
        return index
    }

    override fun getAccess(): Int {
        return params.access
    }

    override fun getName(): String {
        return params.name
    }
}