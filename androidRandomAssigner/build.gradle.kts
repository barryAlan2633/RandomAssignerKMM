import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")

    //hilt
    kotlin("kapt")
    id("dagger.hilt.android.plugin")

}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.3.1")
    // Compose Material Design
    implementation ("androidx.compose.material:material:1.0.2")
    // Animations
    implementation ("androidx.compose.animation:animation:1.0.2")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.0.2")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.0.2")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.17.0")

    implementation ("com.google.accompanist:accompanist-insets-ui:0.19.0")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")


    //leak canary
    debugImplementation ("com.squareup.leakcanary:leakcanary-android:2.7")

    //hilt
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")


}


android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.barryalan.randomassignerkmm.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 2
        versionName = "2.0"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.4"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

// Allow references to generated code hilt
kapt {
    correctErrorTypes = true
}