package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.ApiVoteType

@Serializable
data class ApiResPostListItem(
    val data: ApiPost,
    val userVote: ApiVoteType?
)