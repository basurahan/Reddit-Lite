package link.limecode.reddit.lite.presentation.model

data class UiUserInfo(
    val username: String,
    val email: String
) {
    val initial: String = "${username.first()}${username.last()}"
}