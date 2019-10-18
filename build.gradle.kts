plugins {
    kotlin("multiplatform") version "1.3.50"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm() // Creates a JVM target with the default name 'jvm'
    js()  // JS target named 'js'

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
