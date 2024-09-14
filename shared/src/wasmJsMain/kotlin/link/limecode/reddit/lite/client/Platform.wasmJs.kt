package link.limecode.reddit.lite.client

class WasmPlatform : Platform {

    override val target: Target
        get() = Target.WASM
}

actual fun getPlatform(): Platform = WasmPlatform()