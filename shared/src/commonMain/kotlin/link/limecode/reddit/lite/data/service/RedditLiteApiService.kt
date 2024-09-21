package link.limecode.reddit.lite.data.service

import io.ktor.client.call.body
import io.ktor.client.request.post
import link.limecode.reddit.lite.data.client.KtorClient
import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.service.api.RedditLiteApi

class RedditLiteApiService(private val ktorClient: KtorClient) : RedditLiteApi {

    override suspend fun loginBy(param: ApiReqLogin): ApiResLogin {
        return ktorClient.http.post("login").body<ApiResLogin>()
    }
}