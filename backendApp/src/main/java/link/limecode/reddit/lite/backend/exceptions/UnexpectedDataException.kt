package link.limecode.reddit.lite.backend.exceptions

class UnexpectedDataException(
    override val message: String = "unexpected data received from validation"
) : Throwable(message = message)