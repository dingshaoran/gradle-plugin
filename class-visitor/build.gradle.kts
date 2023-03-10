plugins {
    id("groovy")
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    compileOnly("com.android.tools.build:gradle:7.4.1")
    implementation("org.ow2.asm:asm-util:9.2")
    implementation("org.ow2.asm:asm-commons:9.2")
    implementation("org.json:json:20210307")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    compileOnly(localGroovy())
    compileOnly(gradleApi())
}

project.version = "3.3.1"
apply(from = "../maven-publish.gradle")