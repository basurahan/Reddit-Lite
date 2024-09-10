package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class ApiReqPostListSort(val paramName: String) {
    @SerialName("NEWEST")
    NEWEST("NEWEST"),
    @SerialName("OLDEST")
    OLDEST("OLDEST")
}