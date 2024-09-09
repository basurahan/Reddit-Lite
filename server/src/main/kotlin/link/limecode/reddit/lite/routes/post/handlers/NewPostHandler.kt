package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPostValidation
import link.limecode.reddit.lite.data.model.response.post.isValid
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.usecase.VoteUseCase
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqNewPost?.handleNewPost(
    postDao: PostDao,
    voteUseCase: VoteUseCase
) : ApiResNewPost {
    val validationResult = validate()
    
    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()
    
    val result = postDao.insert(this)
    
    return ApiResNewPost.Success(voteUseCase.upVote(post = result, userId = this.userId))
}

fun ApiReqNewPost?.validate(): ApiResNewPost.Fail? {
    var validation = ApiResNewPostValidation()
    
    if (this?.title.isNullOrBlank()) validation = validation.copy(title = "title is required")
    if (this?.body.isNullOrBlank()) validation = validation.copy(body = "body is required")
    
    if (!validation.isValid())
        return ApiResNewPost.Fail(validation)
    
    return null
}