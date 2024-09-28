package link.limecode.reddit.lite.backend.routes.post.handlers

import link.limecode.reddit.lite.backend.domain.dao.PostDao
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.data.model.response.post.ApiResVotePost

suspend fun ApiReqVotePost?.handleVotePost(
    postDao: PostDao,
    voteUseCase: link.limecode.reddit.lite.backend.domain.usecase.VoteUseCase,
    userId: Long
): ApiResVotePost {
    if (this == null) throw UnexpectedDataException()

    val postData = postDao.getPostBy(this.postId)
    val result = if (this.voteType == ApiVoteType.UPVOTE) {
        voteUseCase.upVote(post = postData, userId = userId)
    } else {
        voteUseCase.downVote(post = postData, userId = userId)
    }

    return ApiResVotePost(result)
}