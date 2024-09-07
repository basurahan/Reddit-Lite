package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiPost

@Serializable
sealed class ApiResNewPost {
    
    @Serializable
    data class Fail(val validation: ApiResNewPostValidation): ApiResNewPost()
    
    @Serializable
    data class Success(val post: ApiPost): ApiResNewPost()
}