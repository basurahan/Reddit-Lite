package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable

@Serializable
data class ApiResNewPostValidation(
    val title: String? = null,
    val body: String? = null,
)

fun ApiResNewPostValidation.isValid(): Boolean {
    return title == null && body == null
}