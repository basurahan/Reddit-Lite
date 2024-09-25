package link.limecode.reddit.lite.domain.validation

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLoginValidation
import link.limecode.reddit.lite.data.model.response.login.isValid

fun ApiReqLogin?.offlineValidation(): ApiResLogin.Fail? {
    var formValidation = ApiResLoginValidation()

    if (this?.username.isNullOrBlank()) formValidation =
        formValidation.copy(username = "username is required")
    if (this?.password.isNullOrBlank()) formValidation =
        formValidation.copy(password = "password is required")

    if (!formValidation.isValid())
        return ApiResLogin.Fail(formValidation)

    return null
}