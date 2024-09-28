package link.limecode.reddit.lite.backend.exceptions

class InvalidTokenException(
    override val message: String = "token parse error"
) : Throwable(message = message)