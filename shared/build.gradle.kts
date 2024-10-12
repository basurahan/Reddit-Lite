import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.cocoapods)
    alias(libs.plugins.kotlin.multiplatform.serialization)
    alias(libs.plugins.kotlin.multiplatform.sqldelight)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.multiplatform.serialization.json)
            implementation(libs.kotlin.multiplatform.datetime)
            implementation(libs.kotlin.multiplatform.ktor.client)
            implementation(project.dependencies.platform(libs.kotlin.multiplatform.koin.bom))
            implementation(libs.kotlin.multiplatform.koin.core)
            implementation(libs.kotlin.multiplatform.ktor.client.plugin.contentnegotiation)
            implementation(libs.kotlin.multiplatform.ktor.client.serialization)
            implementation(libs.kotlin.multiplatform.ktor.client.plugin.logging)
            implementation(libs.kotlin.multiplatform.coroutines.core)
            implementation(libs.kotlin.multiplatform.sqldelight.coroutine)
        }
        androidMain.dependencies {
            implementation(libs.android.ktor.client.engine)
            implementation(libs.android.koin)

            implementation(libs.android.lifecycle)
            implementation(libs.android.livedata)
            implementation(libs.android.saved.state)

            implementation(libs.android.ktor.logger)
            implementation(libs.android.datastore)

            implementation(libs.android.sqldelight.driver)
        }
        iosMain.dependencies {
            implementation(libs.ios.ktor.client.engine)
            implementation(libs.kotlin.multiplatform.koin.core)
            //implementation(libs.kotlin.multiplatform.coroutines.core)
            // TODO: logger for ios
            implementation(libs.ios.sqldelight.driver)
        }
    }
}

android {
    namespace = "link.limecode.reddit.lite"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("link.limecode.reddit.lite.database")
        }
    }
}