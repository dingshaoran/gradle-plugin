plugins {
    id("groovy")
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    compileOnly("com.android.tools.build:gradle:7.4.1")
    compileOnly("com.android.tools:sdk-common:30.4.1")
    compileOnly("com.android.tools:common:30.4.1")
    compileOnly(localGroovy())
    compileOnly(gradleApi())
}

project.version = "1.2.2"
apply(from = "../maven-publish.gradle")