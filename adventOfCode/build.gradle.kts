import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM
    id("org.jetbrains.kotlin.jvm").version("1.3.11")

    // Apply the application to add support for building a CLI application
    application
    id("com.github.johnrengelman.shadow").version("2.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.1")
    }
}
//compileKotlin {
//    kotlinOptions.jvmTarget= 1.8
//}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Use the Kotlin test library
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("no.tornado:tornadofx:1.7.17")

    implementation("com.github.ajalt:mordant:1.2.0")

    testImplementation("org.amshove.kluent:kluent:1.45")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.9.8")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.8")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.8")

}

application {
    // Define the main class for the application
    mainClassName = "AdventOfCode2018.AppKt"
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "AdventOfCode2018.AppKt")
    }
}