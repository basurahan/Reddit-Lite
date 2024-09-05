import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.INT
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.build.konfig)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
        }
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kmp.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "link.limecode.reddit.lite.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

configure<BuildKonfigExtension> {
    packageName = "link.limecode.config"

    val props = Properties()
    val serverPort: String?
    val supbaseUrl: String?
    val supabaseKey: String?
    val jwtUserAuth: String?
    val jwtSecret: String?
    val jwtIssuer: String?
    val jwtAudience: String?
    val jwtRealm: String?
    val jwtClaimUserName: String?
    val jwtClaimUserId: String?
    val fallbackErrorMessage: String?

    try {
        props.load(file("key.properties").inputStream())

        serverPort = props["SERVER_PORT"]?.toString() ?: throw Throwable("missing SERVER_PORT config")
        fallbackErrorMessage = props["FALLBACK_ERROR_MESSAGE"]?.toString() ?: throw Throwable("missing FALLBACK_ERROR_MESSAGE config")
        supbaseUrl = props["SUPABASE_URL"]?.toString() ?: throw Throwable("missing SUPABASE_URL config")
        supabaseKey = props["SUPABASE_KEY"]?.toString() ?: throw Throwable("missing SUPABASE_KEY config")
        jwtUserAuth = props["JWT_USER_AUTH"]?.toString() ?: throw Throwable("missing JWT_USER_AUTH config")
        jwtSecret = props["JWT_SECRET"]?.toString() ?: throw Throwable("missing JWT_SECRET config")
        jwtIssuer = props["JWT_ISSUER"]?.toString() ?: throw Throwable("missing JWT_ISSUER config")
        jwtAudience = props["JWT_AUDIENCE"]?.toString() ?: throw Throwable("missing JWT_AUDIENCE config")
        jwtRealm = props["JWT_REALM"]?.toString() ?: throw Throwable("missing JWT_REALM config")
        jwtClaimUserName = props["JWT_CLAIM_USERNAME"]?.toString() ?: throw Throwable("missing JWT_CLAIM_USERNAME config")
        jwtClaimUserId = props["JWT_CLAIM_USER_ID"]?.toString() ?: throw Throwable("missing JWT_CLAIM_USER_ID config")
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }

    defaultConfigs {
        buildConfigField(
            STRING,
            "FALLBACK_ERROR_MESSAGE",
            fallbackErrorMessage
        )

        buildConfigField(
             INT,
             "SERVER_PORT",
             serverPort
         )

         buildConfigField(
             STRING,
             "SUPABASE_URL",
             supbaseUrl
         )

         buildConfigField(
             STRING,
             "SUPABASE_KEY",
             supabaseKey
         )

         buildConfigField(
             STRING,
             "JWT_USER_AUTH",
             jwtUserAuth
         )

         buildConfigField(
             STRING,
             "JWT_SECRET",
             jwtSecret
         )

         buildConfigField(
             STRING,
             "JWT_ISSUER",
             jwtIssuer
         )

         buildConfigField(
             STRING,
             "JWT_AUDIENCE",
             jwtAudience
         )

         buildConfigField(
             STRING,
             "JWT_REALM",
             jwtRealm
         )

         buildConfigField(
             STRING,
             "JWT_CLAIM_USERNAME",
             jwtClaimUserName
         )

        buildConfigField(
            STRING,
            "JWT_CLAIM_USER_ID",
            jwtClaimUserId
        )
    }
}
