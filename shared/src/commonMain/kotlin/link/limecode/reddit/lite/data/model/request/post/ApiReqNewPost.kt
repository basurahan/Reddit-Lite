package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiReqNewPost(
    val title: String,
    val body: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("subreddit_id")
    val subRedditId: Int,
    @SerialName("upvote_count")
    val upVoteCount: Int = 0,
    @SerialName("downvote_count")
    val downVoteCount: Int = 0
)