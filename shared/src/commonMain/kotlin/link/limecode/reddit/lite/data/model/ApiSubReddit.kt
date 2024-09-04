package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSubReddit(
    val id: Int,
    val name: String,
    val description: String?,
    val logo: String?,
    @SerialName("total_subscribers")
    val totalSubscribers: Int,
    @SerialName("total_post")
    val totalPost: Int,
    @SerialName("total_comments")
    val totalComments: Int
)