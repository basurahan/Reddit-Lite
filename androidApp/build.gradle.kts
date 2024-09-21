plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform.android)
    alias(libs.plugins.kotlin.multiplatform.serialization)
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
    implementation(libs.android.fragment.navigation)
    implementation(libs.android.navigation.ui)
    implementation(libs.kotlin.multiplatform.serialization.json)
    implementation(libs.android.vue.binder)

    implementation(project.dependencies.platform(libs.kotlin.multiplatform.koin.bom))
    implementation(libs.kotlin.multiplatform.koin.core)
    implementation(libs.android.koin)
    implementation(libs.android.koin.viewmodel)

    implementation(libs.android.coroutines)

    implementation(libs.android.lifecycle)
    implementation(libs.android.livedata)
    implementation(libs.android.saved.state)
}