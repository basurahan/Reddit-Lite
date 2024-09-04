package link.limecode.reddit.lite.routes.subreddit

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubreddit
import link.limecode.reddit.lite.domain.dao.SubRedditDao
import link.limecode.reddit.lite.routes.subreddit.handlers.handleNewSubReddit
import link.limecode.reddit.lite.routes.subreddit.resources.Subreddit
import org.koin.ktor.plugin.scope

fun Route.configureSubRedditRoutes() {
    
    post<Subreddit.New> {
        val subRedditDao = call.scope.get<SubRedditDao>()
        
        val requestData = runCatching { call.receive<ApiReqNewSubReddit>() }.getOrNull()
        when (val result = requestData.handleNewSubReddit(subRedditDao)) {
            is ApiResNewSubreddit.Fail -> call.respond(status = HttpStatusCode.UnprocessableEntity, message = result)
            is ApiResNewSubreddit.Success -> call.respond(result)
        }
    }
}