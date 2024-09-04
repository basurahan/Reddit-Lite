package link.limecode.reddit.lite.data.model.response.login

import kotlinx.serialization.Serializable

@Serializable
data class ApiResLoginValidation(
    val username: String? = null,
    val password: String? = null
)

fun ApiResLoginValidation.isValid() : Boolean {
    return username == null && password == null
}