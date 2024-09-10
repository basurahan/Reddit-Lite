package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable

@Serializable
data class ApiResAuthPostList(
    val post: List<ApiResPostListItem>,
    // TODO: cursor should be long
    val cursor: Int?,
    val limit: Long
)