package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiReqPostListFilter {
    
    @SerialName("ALL")
    ALL,
    
    @SerialName("SUBREDDIT")
    SUBREDDIT
}