package link.limecode.reddit.lite.data.repository

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.service.api.RedditLiteApi
import link.limecode.reddit.lite.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(private val apiService: RedditLiteApi) : AuthenticationRepository {

    override suspend fun loginBy(param: ApiReqLogin): ApiResLogin {
        return apiService.loginBy(param)
    }
}