package link.limecode.reddit.lite.domain.converter

import link.limecode.reddit.lite.data.model.ApiUser
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLoginValidation
import link.limecode.reddit.lite.domain.model.UiResLogin
import link.limecode.reddit.lite.domain.model.UiResLoginValidation
import link.limecode.reddit.lite.domain.model.UiUser

fun ApiResLogin.toUiModel(): UiResLogin {
    return when (this) {
        is ApiResLogin.Fail -> UiResLogin.Fail(validation = validation.toUiModel())
        is ApiResLogin.Success -> UiResLogin.Success(user = user.toUiModel(), token = token)
    }
}

fun ApiResLoginValidation.toUiModel(): UiResLoginValidation {
    return UiResLoginValidation(
        username = username,
        password = password
    )
}

fun ApiUser.toUiModel(): UiUser {
    return UiUser(
        id = id,
        username = username,
        email = email
    )
}