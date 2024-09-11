package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable

@Serializable
data class ApiResPostList(
    val items: List<ApiResPostListItem>,
    // TODO: cursor should be long
    val cursor: Long?,
    val limit: Long
)