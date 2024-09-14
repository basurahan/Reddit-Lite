package link.limecode.reddit.lite.client

class IOSPlatform : Platform {

    override val target: Target
        get() = Target.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()