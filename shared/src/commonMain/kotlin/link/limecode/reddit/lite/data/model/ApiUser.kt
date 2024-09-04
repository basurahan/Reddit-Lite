package link.limecode.reddit.lite.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: Int,
    val email: String,
    val username: String,
    val password: String
)