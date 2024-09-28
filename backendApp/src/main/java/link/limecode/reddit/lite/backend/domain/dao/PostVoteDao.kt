package link.limecode.reddit.lite.backend.domain.dao

import link.limecode.reddit.lite.data.model.ApiPostVote
import link.limecode.reddit.lite.data.model.ApiVotePost
import link.limecode.reddit.lite.data.model.ApiVoteType
import link.limecode.reddit.lite.data.model.request.post.ApiReqVotePost

interface PostVoteDao {

    suspend fun insert(vote: ApiReqVotePost): ApiPostVote
    suspend fun insert(vote: ApiVotePost): ApiPostVote
    suspend fun isDownVotedBy(userId: Long, postId: Long): Boolean
    suspend fun isUpVotedBy(userId: Long, postId: Long): Boolean
    suspend fun getVote(userId: Long, postId: Long): ApiPostVote?
    suspend fun updateVote(userId: Long, postId: Long, newVote: ApiVoteType): ApiPostVote
}