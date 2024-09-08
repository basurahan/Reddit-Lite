package link.limecode.reddit.lite.routes.post.handlers

import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost
import link.limecode.reddit.lite.data.model.response.post.ApiResVotePost
import link.limecode.reddit.lite.domain.dao.PostVoteDao
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqVotePost?.handleVotePost(
    postVoteDao: PostVoteDao
): ApiResVotePost {
    if (this == null) throw UnexpectedDataException()
    
    val result = postVoteDao.insert(this)
    
    return ApiResVotePost(result)
}