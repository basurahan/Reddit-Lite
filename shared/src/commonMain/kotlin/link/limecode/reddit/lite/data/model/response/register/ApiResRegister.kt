package link.limecode.reddit.lite.data.model.response.register

import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.data.model.ApiUser

@Serializable
sealed class ApiResRegister {
    
    @Serializable
    data class Fail(val validation: ApiResRegisterValidation) : ApiResRegister()
    
    @Serializable
    data class Success(
        val user: ApiUser,
        val message: String
    ) : ApiResRegister()
}