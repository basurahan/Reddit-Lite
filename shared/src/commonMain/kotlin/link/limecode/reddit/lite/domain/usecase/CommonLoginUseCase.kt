package link.limecode.reddit.lite.domain.usecase

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.domain.converter.toUiModel
import link.limecode.reddit.lite.domain.model.UiResLogin
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository
import link.limecode.reddit.lite.domain.validation.offlineValidation

class CommonLoginUseCase(private val repository: AuthenticationRepository) {

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

    suspend fun invoke(param: Param): UiResLogin {
        val isValid = param.toApiModel().offlineValidation()

        if (isValid != null) {
            return isValid.toUiModel()
        }

        return repository.loginBy(param.toApiModel()).toUiModel()
    }
}