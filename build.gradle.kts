import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
//    kotlin("jvm") version "1.8.0"
    with("1.8.0") {
        kotlin("jvm") version this
        id("org.jetbrains.kotlin.plugin.jpa") version this
        id("org.jetbrains.kotlin.plugin.allopen") version this
    }
}

group = "com.dehydrogenaza"
version = "1.0-SNAPSHOT"

val kotlinVersion = KotlinVersion.KOTLIN_1_8
val jvmVersion = 17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:5.6.14.Final")
    implementation("mysql:mysql-connector-java:8.0.31")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions.apiVersion.set(kotlinVersion)
    compilerOptions.languageVersion.set(kotlinVersion)
    //compilerOptions.jvmTarget.set(JvmTarget.JVM_17) //set automatically by the toolchain
}

kotlin {
    jvmToolchain(jvmVersion)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}