package link.limecode.reddit.lite.backend.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources

fun Application.configureTypeSafetyRouting() {
    install(Resources)
}