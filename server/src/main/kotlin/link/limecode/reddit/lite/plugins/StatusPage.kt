package link.limecode.reddit.lite.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import link.limecode.reddit.lite.config.Constants

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            print("hello man")
            print(Constants.SUPABASE_URL)
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = cause.message ?: Constants.FALLBACK_ERROR_MESSAGE
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