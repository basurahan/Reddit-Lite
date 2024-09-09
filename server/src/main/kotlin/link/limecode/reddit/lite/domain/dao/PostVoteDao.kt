package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost

interface PostVoteDao {
    
    suspend fun insert(vote: ApiReqVotePost): ApiPostVote
    suspend fun isDownVotedBy(userId: Int, postId: Int): Boolean
    suspend fun isUpVotedBy(userId: Int, postId: Int): Boolean
    suspend fun getVote(userId: Int, postId: Int): ApiPostVote?
    suspend fun updateVote(userId: Int, postId: Int, newVote: ApiVoteType): ApiPostVote
}