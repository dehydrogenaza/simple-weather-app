import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
//    kotlin("jvm") version "1.7.21"
    kotlin("jvm") version "1.8.0"
}

group = "com.dehydrogenaza"
version = "1.0-SNAPSHOT"

val kotlinVersion = KotlinVersion.KOTLIN_1_8
val jvmVersion = 17


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//    kotlinOptions {
//        apiVersion = "1.8"
//        languageVersion = "1.8"
//        jvmTarget = "17"
//    }

    compilerOptions.apiVersion.set(kotlinVersion)
    compilerOptions.languageVersion.set(kotlinVersion)
    //compilerOptions.jvmTarget.set(JvmTarget.JVM_17) //set automatically by the toolchain
}

kotlin {
    jvmToolchain(jvmVersion)
}