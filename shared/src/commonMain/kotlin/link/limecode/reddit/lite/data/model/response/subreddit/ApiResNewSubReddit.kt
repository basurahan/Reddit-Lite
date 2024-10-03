package link.limecode.reddit.lite.data.model.response.subreddit

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiSubReddit

@Serializable
sealed class ApiResNewSubreddit {
    
    @Serializable
    data class Fail(val validation: ApiResNewSubRedditValidation): ApiResNewSubreddit()
    
    @Serializable
    data class Success(
        val subReddit: ApiSubReddit 
    ): ApiResNewSubreddit()
}