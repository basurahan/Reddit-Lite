package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiAttachmentType

@Serializable
data class ApiReqNewPostAttachement(
    @SerialName("post_id")
    val postId: Long,
    val name: String,
    @SerialName("attachment_type")
    val attachmentType: ApiAttachmentType
)