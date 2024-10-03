package link.limecode.reddit.lite.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import link.limecode.reddit.lite.util.initKtorClient

class AndroidKtorClient : KtorClient {

    override val http: HttpClient
        get() = initKtorClient(OkHttp.create())
}

actual fun getKtorClient(): KtorClient = AndroidKtorClient()