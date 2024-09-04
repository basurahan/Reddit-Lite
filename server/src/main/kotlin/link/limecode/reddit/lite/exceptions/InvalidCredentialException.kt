package link.limecode.reddit.lite.exceptions

class InvalidCredentialException(
    override val message: String = "wrong username or password"
) : Throwable(message = message)