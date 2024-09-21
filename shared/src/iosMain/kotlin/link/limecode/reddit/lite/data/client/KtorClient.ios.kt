package link.limecode.reddit.lite.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import link.limecode.reddit.lite.data.util.initKtorClient

class IOSKtorClient : KtorClient {

    override val http: HttpClient
        get() = initKtorClient(Darwin.create())
}

actual fun getKtorClient(): KtorClient = IOSKtorClient()