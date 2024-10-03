package link.limecode.reddit.lite.backend.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import link.limecode.reddit.lite.backend.routes.authentication.configureAuthenticationRoutes
import link.limecode.reddit.lite.backend.routes.post.configurePostRoutes
import link.limecode.reddit.lite.backend.routes.subreddit.configureSubRedditRoutes

fun Application.configureRoutes() {
    routing {
        configureAuthenticationRoutes()
        configureSubRedditRoutes()
        configurePostRoutes()
    }
}