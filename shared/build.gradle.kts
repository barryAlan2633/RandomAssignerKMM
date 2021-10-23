import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}


kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    val serializationVersion = "1.2.2"
    val sqlDelightVersion: String by project




    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")

                //Flows
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")


            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting{
            dependencies{
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

            }
        }
        val iosTest by getting
    }
}

android {
    compileSdk=31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk=21
        targetSdk=31
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.example.randomassignerkmm.datasource.cache"
    }
}