package link.limecode.reddit.lite.data.model.response.login

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiUser

@Serializable
sealed class ApiResLogin {
    
    @Serializable
    data class Fail(val validation: ApiResLoginValidation) : ApiResLogin()
    
    @Serializable
    data class Success(
        val user: ApiUser,
        val token: String
    ) : ApiResLogin()
}