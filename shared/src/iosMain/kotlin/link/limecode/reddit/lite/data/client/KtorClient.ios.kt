package link.limecode.reddit.lite.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

class IOSKtorClient : KtorClient {

    override val http: HttpClient
        get() = HttpClient(Darwin.create())
}

actual fun getKtorClient(): KtorClient = IOSKtorClient()