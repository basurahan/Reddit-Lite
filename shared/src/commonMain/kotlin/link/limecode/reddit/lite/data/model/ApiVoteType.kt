package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiVoteType {
    @SerialName("upvote")
    UPVOTE,
    @SerialName("downvote")
    DOWNVOTE
}