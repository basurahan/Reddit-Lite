package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPostVote(
    val id: Long,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("post_id")
    val postId: Long,
    @SerialName("vote_type")
    val voteType: ApiVoteType
)