package link.limecode.reddit.lite.util

import link.limecode.reddit.lite.config.Constants
import link.limecode.reddit.lite.config.IOSConstants
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey

fun Throwable.buildNSError(message: String?): NSError {
    val userInfo: Map<Any?, *> = mapOf(
        NSLocalizedDescriptionKey to (message ?: Constants.FALLBACK_ERROR_MESSAGE)
    )
    return NSError.errorWithDomain(domain = IOSConstants.DOMAIN_VIEW_MODEL_HELPER, code = 1, userInfo = userInfo)
}