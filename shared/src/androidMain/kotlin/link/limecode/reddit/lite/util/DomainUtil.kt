package link.limecode.reddit.lite.util

import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.TimeoutCancellationException
import link.limecode.reddit.lite.config.Constants
import java.io.IOException

class DomainException(override val message: String) : Throwable(message)

inline fun <T, R> T.runDomainCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: RedirectResponseException) {
        requireNotNull(e.localizedMessage) { "Illegal Argument, Domain error message not handled" }
        Result.failure(DomainException(e.localizedMessage!!))
    } catch (e: ServerResponseException) {
        requireNotNull(e.localizedMessage) { "Illegal Argument, Domain error message not handled" }
        Result.failure(DomainException(e.localizedMessage!!))
    } catch (e: TimeoutCancellationException) {
        requireNotNull(e.localizedMessage) { "Illegal Argument, Domain error message not handled" }
        Result.failure(DomainException(e.localizedMessage!!))
    } catch (e: IOException) {
        Result.failure(DomainException(e.localizedMessage ?: Constants.FALLBACK_ERROR_MESSAGE))
    }
}