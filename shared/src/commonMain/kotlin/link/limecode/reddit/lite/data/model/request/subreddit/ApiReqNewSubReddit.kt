package link.limecode.reddit.lite.data.model.request.subreddit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqNewSubReddit(
    val name: String,
    val description: String? = null,
    val logo: String? = null,
    @SerialName("total_subscribers")
    val totalSubscribers: Int = 0,
    @SerialName("total_post")
    val totalPost: Int = 0,
    @SerialName("total_comments")
    val totalComments: Int = 0
)