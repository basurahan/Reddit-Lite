package link.limecode.reddit.lite.data.service.api

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin

interface RedditLiteApi {
    suspend fun loginBy(param: ApiReqLogin): ApiResLogin
}