package link.limecode.reddit.lite.data.model.request.login

import kotlinx.serialization.Serializable

@Serializable
data class ApiReqLogin (
    val username: String,
    val password: String
)