package link.limecode.reddit.lite.backend.exceptions

class InvalidCredentialException(
    override val message: String = "wrong username or password"
) : Throwable(message = message)