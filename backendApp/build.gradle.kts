plugins {
    alias(libs.plugins.kotlin.multiplatform.jvm)
    alias(libs.plugins.kotlin.multiplatform.serialization)
    alias(libs.plugins.ktor.server)
    application
}

group = "link.limecode.reddit.lite"
version = "1.0.0"
application {
    mainClass.set("link.limecode.reddit.lite.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)

    // core
    implementation(libs.ktor.server.plugin.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    // plugins
    implementation(libs.ktor.server.plugin.negotiation)
    implementation(libs.ktor.server.plugin.status)
    implementation(libs.ktor.server.plugin.resources)
    implementation(libs.ktor.server.plugin.auth)
    implementation(libs.ktor.server.plugin.auth.jwt)

    // serialization
    implementation(libs.kotlin.multiplatform.serialization.json)

    // database
    implementation(platform(libs.kotlin.multiplatform.supabase.bom))
    implementation(libs.kotlin.multiplatform.supabase.postgres)
    implementation(libs.ktor.client.apache)

    // koin
    implementation(platform(libs.kotlin.multiplatform.koin.bom))
    implementation(libs.kotlin.multiplatform.koin.core)
    implementation(libs.ktor.koin)
}