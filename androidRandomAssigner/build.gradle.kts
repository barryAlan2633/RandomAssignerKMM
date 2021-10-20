plugins {
    id("com.android.application")
    kotlin("android")
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


}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.example.randomassignerkmm.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
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