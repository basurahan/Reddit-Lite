package link.limecode.reddit.lite.routes.post

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import link.limecode.reddit.lite.config.CommonConstants
import link.limecode.reddit.lite.data.model.request.post.*
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPost
import link.limecode.reddit.lite.domain.dao.PostAttachementDao
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.usecase.PostUseCase
import link.limecode.reddit.lite.domain.usecase.VoteUseCase
import link.limecode.reddit.lite.exceptions.InvalidTokenException
import link.limecode.reddit.lite.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.routes.post.handlers.*
import link.limecode.reddit.lite.routes.post.resources.PostPrivate
import link.limecode.reddit.lite.routes.post.resources.PostPublic
import org.koin.ktor.plugin.scope

fun Route.configurePostRoutes() {
    
    authenticate(CommonConstants.JWT_USER_AUTH) {

        post<PostPrivate.New> {
            val postDao = call.scope.get<PostDao>()
            val voteUseCase = call.scope.get<VoteUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqNewPost>() }.getOrNull()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong() ?: throw InvalidTokenException()
            when (val result = requestData.handleNewPost(postDao = postDao, voteUseCase = voteUseCase, userId = userId)) {
                is ApiResNewPost.Fail -> call.respond(status = HttpStatusCode.UnprocessableEntity, message = result)
                is ApiResNewPost.Success -> call.respond(result)
            }
        }

        post<PostPrivate.Attachment.New> {
            val postAttachementDao = call.scope.get<PostAttachementDao>()

            val requestData = runCatching { call.receiveNullable<ApiReqNewPostAttachement>() }.getOrNull()
            val result = requestData.handleNewPostAttachment(postAttachementDao)
            call.respond(result)
        }

        post<PostPrivate.Vote> {
            val postDao = call.scope.get<PostDao>()
            val voteUseCase = call.scope.get<VoteUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqVotePost>() }.getOrNull()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong() ?: throw InvalidTokenException()
            val result = requestData.handleVotePost(postDao = postDao, voteUseCase = voteUseCase, userId = userId)
            call.respond(result)
        }

        post<PostPrivate> {
            val postUseCase = call.scope.get<PostUseCase>()

            val requestData = runCatching { call.receiveNullable<ApiReqPostList>() }.getOrNull() ?: throw UnexpectedDataException()
            val principal = call.principal<JWTPrincipal>() ?: throw InvalidTokenException()
            val userId = principal.payload.claims[CommonConstants.JWT_CLAIM_USER_ID]?.asLong() ?: throw InvalidTokenException()

            val result = requestData.handlePrivatePostList(
                postUseCase = postUseCase,
                userId = userId
            )

            call.respond(result)
        }
    }

    post<PostPublic> {
        val postUseCase = call.scope.get<PostUseCase>()

        val requestData = runCatching { call.receiveNullable<ApiReqPostList>() }.getOrNull() ?: throw UnexpectedDataException()
        val result = requestData.handlePublicPostList(postUseCase)

        call.respond(result)
    }
}