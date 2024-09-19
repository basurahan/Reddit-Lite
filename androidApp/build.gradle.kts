plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform.android)
}

android {
    namespace = "link.limecode.reddit.lite.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "link.limecode.reddit.lite.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)

    implementation(libs.android.core)
    implementation(libs.android.activity)
    implementation(libs.android.app.compat)
    implementation(libs.android.material)
    implementation(libs.android.constraint.layout)
}