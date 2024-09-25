package link.limecode.reddit.lite.routes.authentication.handlers

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.domain.usecase.AuthUsecase
import link.limecode.reddit.lite.domain.validation.offlineValidation
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqLogin?.loginHandler(
    authUsecase: AuthUsecase
): ApiResLogin {
    val validationResult = offlineValidation()

    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()

    val result = authUsecase.login(
        username = username,
        password = password
    )

    return ApiResLogin.Success(
        user = result.first,
        token = result.second
    )
}

