plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinx.serialization)
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
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)

    // plugins
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.server.status.page)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)

    // serialization
    implementation(libs.ktor.kotlinx.serialization.json)

    // koin for ktor
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)

    // database
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.postgres)
    implementation(libs.ktor.client.apache)

    implementation(libs.kotlinx.datetime)
}