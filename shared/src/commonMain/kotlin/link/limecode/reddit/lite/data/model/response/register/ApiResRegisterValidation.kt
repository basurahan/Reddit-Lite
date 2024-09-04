package link.limecode.reddit.lite.data.model.response.register

import kotlinx.serialization.Serializable

@Serializable
data class ApiResRegisterValidation(
    val email: String? = null,
    val username: String? = null,
    val password: String? = null
)

fun ApiResRegisterValidation.isValid() : Boolean {
    return email == null && username == null && password == null
}