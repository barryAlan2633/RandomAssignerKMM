import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}
// CocoaPods requires the podspec to have a version.
version = "1.0"


kotlin {
    android()

    ios()
    cocoapods {

            framework {
                ios.deploymentTarget = "13.5"

                // Configure fields required by CocoaPods.
                summary = "Some description for a Kotlin/Native module"
                homepage = "Link to a Kotlin/Native module homepage"
                // Framework name configuration. Use this property instead of deprecated 'frameworkName'
                baseName = "MyFramework"
                // (Optional) Dynamic framework support
                isStatic = false
                // (Optional) Dependency export
//                export(project(":anotherKMMModule"))
//                transitiveExport = true
                // (Optional) Bitcode embedding
                embedBitcode(org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode.BITCODE)
            }

            // Maps custom Xcode configuration to NativeBuildType
            xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
            xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        }

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

                //Flows need native-mt for xcode error
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")

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