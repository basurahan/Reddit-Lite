package link.limecode.reddit.lite.data.model.request.subreddit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqJoinSubReddit(
    @SerialName("subreddit_id")
    val subRedditId: Long
)