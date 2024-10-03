package link.limecode.reddit.lite.data.model.response.subreddit

import kotlinx.serialization.Serializable

@Serializable
data class ApiResNewSubRedditValidation(
    val name: String? = null
)

fun ApiResNewSubRedditValidation.isValid(): Boolean {
    return name == null
}