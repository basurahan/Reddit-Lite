package link.limecode.reddit.lite.client

class AndroidPlatform : Platform {

    override val target: Target
        get() = Target.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()