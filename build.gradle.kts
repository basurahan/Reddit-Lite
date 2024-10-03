plugins {
    // android plugins
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // kotlin multiplatform plugins
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.multiplatform.android) apply false
    alias(libs.plugins.kotlin.multiplatform.cocoapods) apply false
    alias(libs.plugins.kotlin.multiplatform.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform.serialization) apply false
}
