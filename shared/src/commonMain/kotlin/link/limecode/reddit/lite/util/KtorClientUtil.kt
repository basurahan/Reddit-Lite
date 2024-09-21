package link.limecode.reddit.lite.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import link.limecode.reddit.lite.config.Constants.CLIENT_BASE_URL
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin

fun initKtorClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        expectSuccess = true

        install(Logging) {
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            url(CLIENT_BASE_URL)
            headers.appendIfNameAbsent(HttpHeaders.ContentType, "application/json")
        }

        val module = SerializersModule {
            polymorphic(ApiResLogin::class) {
                subclass(ApiResLogin.Success::class)
                subclass(ApiResLogin.Fail::class)
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    serializersModule = module
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }
    }
}