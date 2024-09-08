package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiPostVote

@Serializable
data class ApiResVotePost(
    val vote: ApiPostVote
)