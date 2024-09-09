package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiVoteType {
    @SerialName("UPVOTE")
    UPVOTE,
    @SerialName("DOWNVOTE")
    DOWNVOTE
}