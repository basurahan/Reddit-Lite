package link.limecode.reddit.lite.exceptions

class AlreadyJoinedException(
    override val message: String = "already joined"
) : Throwable(message = message)