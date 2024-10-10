package link.limecode.reddit.lite.util

import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.TimeoutCancellationException
import link.limecode.reddit.lite.config.CommonConstants

class CommonDomainException(override val message: String) : CommonDomainParentException(message)

suspend inline fun <T, R> T.runCommonDomainCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: RedirectResponseException) {
        Result.failure(CommonDomainException(e.response.bodyAsText()))
    } catch (e: ServerResponseException) {
        Result.failure(CommonDomainException(e.response.bodyAsText()))
    } catch (e: TimeoutCancellationException) {
        Result.failure(CommonDomainException(CommonConstants.TIMEOUT_ERROR_MESSAGE))
    }
}