package link.limecode.reddit.lite.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPost(
    val id: Long,
    val title: String,
    val body: String,
    @SerialName("user_id")
    val userId: Long,
    @SerialName("subreddit_id")
    val subredditId: Long,
    @SerialName("upvote_count")
    val upvoteCount: Int,
    @SerialName("downvote_count")
    val downvoteCount: Int,
    @SerialName("created_at")
    val createdAt: Instant
)