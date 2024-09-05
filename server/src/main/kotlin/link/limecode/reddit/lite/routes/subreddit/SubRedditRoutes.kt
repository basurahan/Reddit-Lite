package link.limecode.reddit.lite.routes.subreddit

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.config.Constants
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqJoinSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubreddit
import link.limecode.reddit.lite.domain.dao.SubRedditDao
import link.limecode.reddit.lite.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.exceptions.InvalidTokenException
import link.limecode.reddit.lite.routes.subreddit.handlers.handleJoinSubReddit
import link.limecode.reddit.lite.routes.subreddit.handlers.handleNewSubReddit
import link.limecode.reddit.lite.routes.subreddit.resources.Subreddit
import org.koin.ktor.plugin.scope

fun Route.configureSubRedditRoutes() {
    
    authenticate(Constants.JWT_USER_AUTH) {

        post<Subreddit.New> {
            val subRedditDao = call.scope.get<SubRedditDao>()
            val subRedditUsersDao = call.scope.get<SubRedditUsersDao>()

            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[Constants.JWT_CLAIM_USER_ID]?.asInt() ?: throw InvalidTokenException()
            val requestData = runCatching { call.receive<ApiReqNewSubReddit>() }.getOrNull()

            val result = requestData.handleNewSubReddit(
                subRedditDao = subRedditDao,
                subRedditUsersDao = subRedditUsersDao,
                userId = userId
            )

            when (result) {
                is ApiResNewSubreddit.Fail -> call.respond(status = HttpStatusCode.UnprocessableEntity, message = result)
                is ApiResNewSubreddit.Success -> call.respond(result)
            }
        }

        post<Subreddit.Join> {
            val subRedditUsersDao = call.scope.get<SubRedditUsersDao>()

            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[Constants.JWT_CLAIM_USER_ID]?.asInt() ?: throw InvalidTokenException()
            val requestData = runCatching { call.receive<ApiReqJoinSubReddit>() }.getOrNull()

            requestData.handleJoinSubReddit(
                subRedditUsersDao = subRedditUsersDao,
                userId = userId
            )

            call.respond(
                status = HttpStatusCode.Created,
                message = ""
            )
        }
    }
}