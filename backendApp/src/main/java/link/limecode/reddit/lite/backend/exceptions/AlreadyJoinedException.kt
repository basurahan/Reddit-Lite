package link.limecode.reddit.lite.backend.exceptions

class AlreadyJoinedException(
    override val message: String = "already joined"
) : Throwable(message = message)