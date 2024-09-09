package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.data.model.response.post.ApiResVotePost
import link.limecode.reddit.lite.domain.dao.PostDao
import link.limecode.reddit.lite.domain.dao.PostVoteDao
import link.limecode.reddit.lite.domain.usecase.VoteUseCase
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqVotePost?.handleVotePost(
    postDao: PostDao,
    voteUseCase: VoteUseCase
): ApiResVotePost {
    if (this == null) throw UnexpectedDataException()
    
    val postData = postDao.getPostBy(this.postId)
    val result = if (this.voteType == ApiVoteType.UPVOTE) {
        voteUseCase.upVote(post = postData, userId = this.userId)
    } else {
        voteUseCase.downVote(post = postData, userId = this.userId)
    }
    
    return ApiResVotePost(result)
}