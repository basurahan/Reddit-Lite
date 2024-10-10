package link.limecode.reddit.lite.util

import io.ktor.utils.io.errors.IOException
import link.limecode.reddit.lite.config.CommonConstants

class AndroidDomainException(override val message: String) : CommonDomainParentException(message)

inline fun <T, R> T.runAndroidDomainCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: IOException) {
        Result.failure(
            AndroidDomainException(
                e.message ?: CommonConstants.FALLBACK_ERROR_MESSAGE
            )
        )
    }
}