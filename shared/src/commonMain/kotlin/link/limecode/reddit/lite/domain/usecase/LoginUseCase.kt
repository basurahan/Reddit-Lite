package link.limecode.reddit.lite.domain.usecase

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository
import link.limecode.reddit.lite.domain.validation.offlineValidation

class LoginUseCase(private val repository: AuthenticationRepository) {

    data class Param(
        val username: String,
        val password: String
    ) {
        fun toApiModel(): ApiReqLogin {
            return ApiReqLogin(
                username = username,
                password = password
            )
        }
    }

    suspend fun invoke(param: Param): ApiResLogin {
        val isValid = param.toApiModel().offlineValidation()

        if (isValid != null) {
            return isValid
        }

        return repository.loginBy(param.toApiModel())
    }
}