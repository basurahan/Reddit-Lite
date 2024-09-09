package link.limecode.reddit.lite.routes.post

import link.limecode.reddit.lite.routes.post.resources.Post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPost
import link.limecode.reddit.lite.domain.dao.PostAttachementDao
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.usecase.VoteUseCase
import link.limecode.reddit.lite.routes.post.handlers.handleNewPost
import link.limecode.reddit.lite.routes.post.handlers.handleNewPostAttachment
import link.limecode.reddit.lite.routes.post.handlers.handleVotePost
import org.koin.ktor.plugin.scope

fun Route.configurePostRoutes() {
    
    post<Post.New> {
        val postDao = call.scope.get<PostDao>()
        val voteUseCase = call.scope.get<VoteUseCase>()
        
        val requestData = runCatching { call.receiveNullable<ApiReqNewPost>() }.getOrNull()
        when (val result = requestData.handleNewPost(postDao = postDao, voteUseCase = voteUseCase)) {
            is ApiResNewPost.Fail -> call.respond(status = HttpStatusCode.UnprocessableEntity, message = result)
            is ApiResNewPost.Success -> call.respond(result)
        }
    }

    post<Post.Attachment.New> {
        val postAttachementDao = call.scope.get<PostAttachementDao>()

        val requestData = runCatching { call.receiveNullable<ApiReqNewPostAttachement>() }.getOrNull()
        val result = requestData.handleNewPostAttachment(postAttachementDao)
        call.respond(result)
    }

    post<Post.Vote> {
        val postDao = call.scope.get<PostDao>()
        val voteUseCase = call.scope.get<VoteUseCase>()

        val requestData = runCatching { call.receiveNullable<ApiReqVotePost>() }.getOrNull()
        val result = requestData.handleVotePost(postDao = postDao, voteUseCase = voteUseCase)
        call.respond(result)
    }
}