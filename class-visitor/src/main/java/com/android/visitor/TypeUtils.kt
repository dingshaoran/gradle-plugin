package com.android.visitor

object TypeUtils {

    fun match(configMethod: Map<String, Any?>, internalName: String): Boolean {
        val exclude = configMethod.get(Constants.KEY_EXCLUDE) as? Collection<*>
        if (exclude != null) {
            return !exclude.contains(internalName)
        }
        val include = configMethod.get(Constants.KEY_INCLUDE) as? Collection<*>
        if (include != null) {
            return include.contains(internalName)
        }
        return true //啥都没配默认匹配中
    }

    /**
     * 生成一个方法描述，在前面插入参数
     * @param descriptor 原方法描述
     * @param owner 配置的描述，可以是 object 类型，可以是整个方法描述
     * @param preObjectName 是否需要插入的第一个参数
     */
    fun getInvokeDescriptor(descriptor: String, owner: String, static: Boolean, preObjectName: String = ""): String {
        val tmp = owner
        return if (tmp.contains("(") && tmp.contains(")")) {
            return tmp
        } else if (static) {
            descriptor.replace("(", "($preObjectName")
        } else if (tmp.endsWith(";")) {
            descriptor.replace("(", "($preObjectName$tmp")
        } else {
            descriptor.replace("(", "(${preObjectName}L${tmp};")
        }
    }
}