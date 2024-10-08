package link.limecode.reddit.lite.backend.routes.post.handlers

import link.limecode.reddit.lite.backend.domain.dao.PostDao
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPost
import link.limecode.reddit.lite.data.model.response.post.ApiResNewPostValidation
import link.limecode.reddit.lite.data.model.response.post.isValid

suspend fun ApiReqNewPost?.handleNewPost(
    postDao: PostDao,
    voteUseCase: link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase,
    userId: Long
): ApiResNewPost {
    val validationResult = validate()

    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()

    val result = postDao.insert(this)

    return ApiResNewPost.Success(voteUseCase.upVote(post = result, userId = userId))
}

fun ApiReqNewPost?.validate(): ApiResNewPost.Fail? {
    var validation = ApiResNewPostValidation()

    if (this?.title.isNullOrBlank()) validation = validation.copy(title = "title is required")
    if (this?.body.isNullOrBlank()) validation = validation.copy(body = "body is required")

    if (!validation.isValid())
        return ApiResNewPost.Fail(validation)

    return null
}