import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

companion object Versions {
    val kotlin = KotlinVersion.KOTLIN_1_8
    const val jvm = 17
    const val hibernate = "5.6.14.Final"
    const val mySqlConnector = "8.0.31"
    const val retrofit = "2.9.0"
//    const val okHttpLoggingInterceptor = "4.10.0"
    //const val gson = "2.10.1"
    const val dateTime = "0.4.0"
}

plugins {
    with("1.8.0") {//language level
        kotlin("jvm") version this
        id("org.jetbrains.kotlin.plugin.jpa") version this
        id("org.jetbrains.kotlin.plugin.allopen") version this
    }
}

group = "com.dehydrogenaza"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:${Versions.hibernate}")
    implementation("mysql:mysql-connector-java:${Versions.mySqlConnector}")
    //implementation("com.google.code.gson:gson:${Versions.gson}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
//    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions.apiVersion.set(Versions.kotlin)
    compilerOptions.languageVersion.set(Versions.kotlin)
    //compilerOptions.jvmTarget.set(JvmTarget.JVM_17) //set automatically by the toolchain
}

kotlin {
    jvmToolchain(Versions.jvm)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}