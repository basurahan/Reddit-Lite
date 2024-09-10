package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiVotePost(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("post_id")
    val postId: Int,
    @SerialName("vote_type")
    val voteType: ApiVoteType
)