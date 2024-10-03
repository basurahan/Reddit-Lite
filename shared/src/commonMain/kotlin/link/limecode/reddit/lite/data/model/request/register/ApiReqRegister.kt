package link.limecode.reddit.lite.data.model.request.register

import kotlinx.serialization.Serializable

@Serializable
data class ApiReqRegister(
    val email: String,
    val username: String,
    val password: String
)