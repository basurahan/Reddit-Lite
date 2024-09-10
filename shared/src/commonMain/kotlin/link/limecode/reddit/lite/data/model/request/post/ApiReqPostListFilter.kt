package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiReqPostListFilter(val paramName: String) {
    
    @SerialName("ALL")
    ALL("ALL"),
    
    @SerialName("SUBREDDIT")
    SUBREDDIT("SUBREDDIT"),
    
    @SerialName("POPULAR")
    POPULAR("POPULAR")
}