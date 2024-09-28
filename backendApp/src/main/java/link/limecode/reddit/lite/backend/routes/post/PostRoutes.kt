package link.limecode.reddit.lite.backend.routes.post

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receiveNullable
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.backend.domain.dao.PostAttachmentDao
import link.limecode.reddit.lite.backend.domain.dao.PostDao
import link.limecode.reddit.lite.backend.domain.usecase.PostUseCase
import link.limecode.reddit.lite.backend.exceptions.InvalidTokenException
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.backend.routes.post.handlers.handleNewPost
import link.limecode.reddit.lite.backend.routes.post.handlers.handleNewPostAttachment
import link.limecode.reddit.lite.backend.routes.post.handlers.handlePrivatePostList
import link.limecode.reddit.lite.backend.routes.post.handlers.handlePublicPostList
import link.limecode.reddit.lite.backend.routes.post.handlers.handleVotePost
import link.limecode.reddit.lite.backend.routes.post.resources.PostPrivate
import link.limecode.reddit.lite.backend.routes.post.resources.PostPublic
import link.limecode.reddit.lite.config.CommonConstants
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement
import link.limecode.reddit.lite.data.model.request.post.ApiReqPostList
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPost
import org.koin.ktor.plugin.scope

fun Route.configurePostRoutes() {

    authenticate(CommonConstants.JWT_USER_AUTH) {

        post<PostPrivate.New> {
            val postDao = call.scope.get<PostDao>()
            val voteUseCase =
                call.scope.get<link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqNewPost>() }.getOrNull()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong()
                ?: throw InvalidTokenException()
            when (val result = requestData.handleNewPost(
                postDao = postDao,
                voteUseCase = voteUseCase,
                userId = userId
            )) {
                is ApiResNewPost.Fail -> call.respond(
                    status = HttpStatusCode.UnprocessableEntity,
                    message = result
                )

                is ApiResNewPost.Success -> call.respond(result)
            }
        }

        post<PostPrivate.Attachment.New> {
            val postAttachmentDao = call.scope.get<PostAttachmentDao>()

            val requestData =
                runCatching { call.receiveNullable<ApiReqNewPostAttachement>() }.getOrNull()
            val result = requestData.handleNewPostAttachment(postAttachmentDao)
            call.respond(result)
        }

        post<PostPrivate.Vote> {
            val postDao = call.scope.get<PostDao>()
            val voteUseCase =
                call.scope.get<link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqVotePost>() }.getOrNull()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong()
                ?: throw InvalidTokenException()
            val result = requestData.handleVotePost(
                postDao = postDao,
                voteUseCase = voteUseCase,
                userId = userId
            )
            call.respond(result)
        }

        post<PostPrivate> {
            val postUseCase = call.scope.get<PostUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqPostList>() }.getOrNull()
                ?: throw UnexpectedDataException()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong()
                ?: throw InvalidTokenException()

            val result = requestData.handlePrivatePostList(
                postUseCase = postUseCase,
                userId = userId
            )

            call.respond(result)
        }
    }

    post<PostPublic> {
        val postUseCase = call.scope.get<PostUseCase>()

        val requestData = runCatching { call.receiveNullable<ApiReqPostList>() }.getOrNull()
            ?: throw UnexpectedDataException()
        val result = requestData.handlePublicPostList(postUseCase)

        call.respond(result)
    }
}