package link.limecode.reddit.lite.backend.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import link.limecode.reddit.lite.config.CommonConstants

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = cause.message ?: CommonConstants.FALLBACK_ERROR_MESSAGE
            )
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                status = status,
                message = status.description.lowercase()
            )
        }

        status(HttpStatusCode.MethodNotAllowed) { call, status ->
            call.respond(
                status = status,
                message = status.description.lowercase()
            )
        }
    }
}