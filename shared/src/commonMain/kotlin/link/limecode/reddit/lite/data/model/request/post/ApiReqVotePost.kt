package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiVoteType

@Serializable
data class ApiReqVotePost(
    @SerialName("post_id")
    val postId: Long,
    @SerialName("vote_type")
    val voteType: ApiVoteType
)