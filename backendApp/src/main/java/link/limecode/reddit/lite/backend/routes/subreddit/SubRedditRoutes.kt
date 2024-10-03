package link.limecode.reddit.lite.backend.routes.subreddit

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.backend.domain.dao.SubRedditDao
import link.limecode.reddit.lite.backend.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.backend.exceptions.InvalidTokenException
import link.limecode.reddit.lite.backend.routes.subreddit.handlers.handleJoinSubReddit
import link.limecode.reddit.lite.backend.routes.subreddit.handlers.handleNewSubReddit
import link.limecode.reddit.lite.backend.routes.subreddit.resources.Subreddit
import link.limecode.reddit.lite.config.CommonConstants
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqJoinSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubreddit
import org.koin.ktor.plugin.scope

fun Route.configureSubRedditRoutes() {

    authenticate(CommonConstants.JWT_USER_AUTH) {

        post<Subreddit.New> {
            val subRedditDao = call.scope.get<SubRedditDao>()
            val subRedditUsersDao = call.scope.get<SubRedditUsersDao>()

            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong()
                ?: throw InvalidTokenException()
            val requestData = runCatching { call.receive<ApiReqNewSubReddit>() }.getOrNull()

            val result = requestData.handleNewSubReddit(
                subRedditDao = subRedditDao,
                subRedditUsersDao = subRedditUsersDao,
                userId = userId
            )

            when (result) {
                is ApiResNewSubreddit.Fail -> call.respond(
                    status = HttpStatusCode.UnprocessableEntity,
                    message = result
                )

                is ApiResNewSubreddit.Success -> call.respond(result)
            }
        }

        post<Subreddit.Join> {
            val subRedditUsersDao = call.scope.get<SubRedditUsersDao>()

            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong()
                ?: throw InvalidTokenException()
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