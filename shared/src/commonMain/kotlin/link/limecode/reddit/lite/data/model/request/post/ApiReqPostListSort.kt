package link.limecode.reddit.lite.data.model.request.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class ApiReqPostListSort {

    @SerialName("NEWEST")
    NEWEST,

    @SerialName("OLDEST")
    OLDEST
}