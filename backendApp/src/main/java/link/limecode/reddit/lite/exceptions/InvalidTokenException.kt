package link.limecode.reddit.lite.exceptions

class InvalidTokenException(
    override val message: String = "token parse error"
) : Throwable(message = message)