package link.limecode.reddit.lite.domain.model

sealed class UiResLogin {

    data class Fail(val validation: UiResLoginValidation) : UiResLogin()

    data class Success(
        val user: UiUser,
        val token: String
    ) : UiResLogin()
}