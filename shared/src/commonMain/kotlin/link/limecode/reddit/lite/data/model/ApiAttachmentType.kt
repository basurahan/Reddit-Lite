package link.limecode.reddit.lite.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiAttachmentType {
    @SerialName("jpg")
    JPG,
    @SerialName("gif")
    GIFG
}
