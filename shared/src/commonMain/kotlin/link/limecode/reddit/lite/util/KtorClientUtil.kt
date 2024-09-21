package link.limecode.reddit.lite.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json
import link.limecode.reddit.lite.config.Constants.CLIENT_BASE_URL

fun initKtorClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        expectSuccess = true

        install(DefaultRequest) {
            url(CLIENT_BASE_URL)
            headers.appendIfNameAbsent(HttpHeaders.ContentType, "application/json")
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }
    }
}