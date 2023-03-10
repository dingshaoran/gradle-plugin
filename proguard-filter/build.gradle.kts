plugins {
    id("groovy")
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    compileOnly("com.android.tools.build:gradle:7.4.1")
    compileOnly(localGroovy())
    compileOnly(gradleApi())
}

project.version = "1.3.4"
apply(from = "../maven-publish.gradle")