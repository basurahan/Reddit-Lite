package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiVoteType

@Serializable
data class ApiReqVotePost(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("post_id")
    val postId: Int,
    @SerialName("vote_type")
    val voteType: ApiVoteType
)