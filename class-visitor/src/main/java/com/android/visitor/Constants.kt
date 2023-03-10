package com.android.visitor

import org.objectweb.asm.Opcodes

object Constants {
    const val API_VER = Opcodes.ASM8
    const val KEY_REPLACEDEFINE = "replaceDefine"
    const val KEY_REPLACECALL = "replaceCall"
    const val KEY_REPLACESTRING = "replaceString"
    const val KEY_ANNOTATION = "annotation"
    const val KEY_RISKCHECK = "riskCheck"
    const val KEY_INVOKE = "invoke"
    const val KEY_EXCLUDE = "exclude"
    const val KEY_INCLUDE = "include"
    const val KEY_TYPE_EXTENDS = "extends"
    const val KEY_TYPE_STRING = "string"
    const val KEY_TYPE_NEW = "new"
}