package link.limecode.reddit.lite.backend

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import link.limecode.reddit.lite.backend.plugins.configureDependencyInjection
import link.limecode.reddit.lite.backend.plugins.configureErrorHandling
import link.limecode.reddit.lite.backend.plugins.configureJwt
import link.limecode.reddit.lite.backend.plugins.configureParsing
import link.limecode.reddit.lite.backend.plugins.configureRoutes
import link.limecode.reddit.lite.backend.plugins.configureTypeSafetyRouting
import link.limecode.reddit.lite.config.CommonConstants

fun main() {
    embeddedServer(
        factory = Netty,
        port = CommonConstants.SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureDependencyInjection()
    configureErrorHandling()
    configureParsing()
    configureJwt()
    configureTypeSafetyRouting()
    configureRoutes()
}