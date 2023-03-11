根据 android.build.tools 7.2 接口提供三种 gradle 插件源码

# class-visitor 
参数含义： transform-config.gradle 文件中有注释每个参数
支持功能：处理 class 中的内容
   3. annotation 替换注解方法
   4. replaceDefine 替换方法定义
   5. replaceCall 替换方法调用
   6. riskCheck 审核风险内容检查
解决问题：
   1. 以 maven 方式提供的三方库中有 bug 编译源码费时费力
   2. 发布应用商店时静态调用某些方法或字符串导致审核失败
   3. 统一替换注解方法实现   

# proguard-filter
参数含义： {原有配置，替换后的配置}
支持功能：过滤所有 proguard 文件中的内容
解决问题：
   1. 依赖库中配置 keep .R$* 禁止 R 文件内联包，导致体积大
   2. 依赖库中配置 dontoptimize 导致代码优化不生效，方法不能内联等
   3. 其他配置导致 assumenosideeffects 等导致 Log 不能自动删除等

# check-resources
参数含义：
   1. app=true 同名资源任一定义出现在 com.android.application 则报错
   2. module=true 同名资源任一定义出现在 com.android.library 目录则报错
   3. maven=true 同名资源全部定义出现在 maven 依赖库则报错
支持功能：检查重复资源
解决问题：大型项目 app 下不引用所有必要资源，module 又特别多。导致同名 res 不确定性覆盖。
