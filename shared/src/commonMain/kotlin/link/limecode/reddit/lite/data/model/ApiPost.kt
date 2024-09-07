package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPost(
    val id: Int,
    val title: String,
    val body: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("subreddit_id")
    val subRedditId: Int
)