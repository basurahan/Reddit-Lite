package link.limecode.reddit.lite.client

class JVMPlatform: Platform {

    override val target: Target
        get() = Target.JVM
}

actual fun getPlatform(): Platform = JVMPlatform()