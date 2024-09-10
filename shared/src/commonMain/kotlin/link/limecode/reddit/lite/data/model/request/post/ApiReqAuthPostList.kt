package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqAuthPostList(
    // TODO: cursor should be long
        @SerialName("subreddit_id")
    val subredditId: Int? = null,
    val cursor: Int? = null,
    val limit: Long,
    val filter: ApiReqPostListFilter,
    val sort: ApiReqPostListSort
)