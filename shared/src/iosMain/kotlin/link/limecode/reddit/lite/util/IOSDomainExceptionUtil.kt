package link.limecode.reddit.lite.util

import io.ktor.client.engine.darwin.DarwinHttpRequestException
import io.ktor.utils.io.errors.IOException
import link.limecode.reddit.lite.config.CommonConstants

class IOSDomainException(override val message: String) : CommonDomainParentException(message)

inline fun <T, R> T.runIOSCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: IOException) {
        if (e is DarwinHttpRequestException) {
            val nsError = e.origin
            val message = nsError.localizedDescription
            Result.failure(IOSDomainException(message))
        } else {
            Result.failure(
                CommonDomainException(
                    e.message ?: CommonConstants.FALLBACK_ERROR_MESSAGE
                )
            )
        }
    }
}