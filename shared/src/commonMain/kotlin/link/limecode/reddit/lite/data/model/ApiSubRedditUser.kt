package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSubRedditUser(
    val id: Int,
    @SerialName("subreddit_id")
    val subredditId: Int,
    @SerialName("user_id")
    val userId: Int
)