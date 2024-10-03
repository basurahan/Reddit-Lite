package link.limecode.reddit.lite.util

import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException
import link.limecode.reddit.lite.config.CommonConstants

class CommonDomainException(override val message: String) : Throwable(message)

suspend inline fun <T, R> T.runDomainCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: RedirectResponseException) {
        Result.failure(CommonDomainException(e.response.bodyAsText()))
    } catch (e: ServerResponseException) {
        Result.failure(CommonDomainException(e.response.bodyAsText()))
    } catch (e: TimeoutCancellationException) {
        Result.failure(CommonDomainException(CommonConstants.TIMEOUT_ERROR_MESSAGE))
    } catch (e: IOException) {
        Result.failure(CommonDomainException(e.message ?: CommonConstants.FALLBACK_ERROR_MESSAGE))
    }
}