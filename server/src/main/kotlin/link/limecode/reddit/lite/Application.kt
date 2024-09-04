package link.limecode.reddit.lite

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import link.limecode.reddit.lite.config.Constants
import link.limecode.reddit.lite.plugins.*

fun main() {
    embeddedServer(Netty, port = Constants.SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDependencyInjection()
    configureErrorHandling()
    configureParsing()
    configureJwt()
    configureTypeSafetyRouting()
    configureRoutes()
}