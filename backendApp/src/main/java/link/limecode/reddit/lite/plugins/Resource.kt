package link.limecode.reddit.lite.plugins

import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.configureTypeSafetyRouting() {
    install(Resources)
}