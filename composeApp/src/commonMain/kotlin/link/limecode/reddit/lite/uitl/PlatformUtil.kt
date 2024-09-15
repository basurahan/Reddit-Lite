package link.limecode.reddit.lite.uitl

import link.limecode.reddit.lite.client.Target

fun Target.isMobile(): Boolean {
    return when (this) {
        Target.ANDROID -> true
        Target.IOS -> true
        Target.JVM -> false
        Target.WASM -> false
    }
}

fun Target.isDesktop(): Boolean {
    return when (this) {
        Target.ANDROID -> false
        Target.IOS -> false
        Target.JVM -> true
        Target.WASM -> true
    }
}