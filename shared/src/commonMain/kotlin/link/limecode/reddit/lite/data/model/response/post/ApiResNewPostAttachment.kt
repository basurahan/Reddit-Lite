package link.limecode.reddit.lite.data.model.response.post

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiPostAttachment

@Serializable
data class ApiResNewPostAttachment(
    val attachment: ApiPostAttachment
)