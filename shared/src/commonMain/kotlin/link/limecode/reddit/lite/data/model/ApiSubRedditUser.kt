package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSubRedditUser(
    val id: Long,
    @SerialName("subreddit_id")
    val subredditId: Long,
    @SerialName("user_id")
    val userId: Long
)