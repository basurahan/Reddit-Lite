package link.limecode.reddit.lite.util

import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.TimeoutCancellationException
import link.limecode.reddit.lite.config.Constants
import java.io.IOException

class DomainException(override val message: String) : Throwable(message)

suspend inline fun <T, R> T.runDomainCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: RedirectResponseException) {
        Result.failure(DomainException(e.response.bodyAsText()))
    } catch (e: ServerResponseException) {
        Result.failure(DomainException(e.response.bodyAsText()))
    } catch (e: TimeoutCancellationException) {
        Result.failure(DomainException(Constants.TIMEOUT_ERROR_MESSAGE))
    } catch (e: IOException) {
        Result.failure(DomainException(e.localizedMessage ?: Constants.FALLBACK_ERROR_MESSAGE))
    }
}