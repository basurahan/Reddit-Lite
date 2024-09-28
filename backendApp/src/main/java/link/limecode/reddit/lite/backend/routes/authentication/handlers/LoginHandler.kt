package link.limecode.reddit.lite.backend.routes.authentication.handlers

import link.limecode.reddit.lite.backend.domain.usecase.AuthUseCase
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.validation.offlineValidation

suspend fun ApiReqLogin?.loginHandler(
    authUseCase: AuthUseCase
): ApiResLogin {
    val validationResult = offlineValidation()

    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()

    val result = authUseCase.login(
        username = username,
        password = password
    )

    return ApiResLogin.Success(
        user = result.first,
        token = result.second
    )
}

