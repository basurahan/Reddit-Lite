package link.limecode.reddit.lite.data.model.request.subreddit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqNewSubRedditUser(
    @SerialName("subreddit_id")
    val subredditId: Long,
    @SerialName("user_id")
    val userId: Long
)