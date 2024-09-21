package link.limecode.reddit.lite.data.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

class AndroidKtorClient : KtorClient {

    override val http: HttpClient
        get() = HttpClient(OkHttp.create())
}

actual fun getKtorClient(): KtorClient = AndroidKtorClient()