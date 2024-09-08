package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost

interface PostVoteDao {
    
    suspend fun insert(vote: ApiReqVotePost): ApiPostVote
}