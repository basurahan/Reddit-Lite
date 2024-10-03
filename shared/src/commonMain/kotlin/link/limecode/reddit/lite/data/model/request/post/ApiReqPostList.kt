package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqPostList(
    // TODO: cursor should be long
    @SerialName("subreddit_id")
    val subredditId: Long? = null,
    val cursor: Long? = null,
    val limit: Long,
    val filter: ApiReqPostListFilter,
    val sort: ApiReqPostListSort
)