package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPostAttachment(
    val id: Int,
    @SerialName("post_id")
    val postId: Int,
    val name: String,
    @SerialName("attachment_type")
    val attachmentType: ApiAttachmentType
)