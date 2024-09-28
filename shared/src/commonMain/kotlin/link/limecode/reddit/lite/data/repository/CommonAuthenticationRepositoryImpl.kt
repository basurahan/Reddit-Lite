package link.limecode.reddit.lite.data.repository

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.service.api.RedditLiteApi
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository

class CommonAuthenticationRepositoryImpl(private val apiService: RedditLiteApi) :
    AuthenticationRepository {

    override suspend fun loginBy(param: ApiReqLogin): ApiResLogin {
        return try {
            apiService.loginBy(param)
        } catch (e: ClientRequestException) {
            e.response.body<ApiResLogin>()
        }
    }
}