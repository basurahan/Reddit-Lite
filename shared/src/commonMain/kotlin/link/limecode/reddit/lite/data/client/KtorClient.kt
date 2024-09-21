package link.limecode.reddit.lite.data.client

import io.ktor.client.HttpClient

interface KtorClient {
    val http: HttpClient
}

expect fun getKtorClient(): KtorClient