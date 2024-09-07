package link.limecode.reddit.lite.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import link.limecode.reddit.lite.routes.authentication.configureAuthenticationRoutes
import link.limecode.reddit.lite.routes.post.configurePostRoutes
import link.limecode.reddit.lite.routes.subreddit.configureSubRedditRoutes

fun Application.configureRoutes() {
    routing {
        configureAuthenticationRoutes()
        configureSubRedditRoutes()
        configurePostRoutes()
    }
}