package link.limecode.reddit.lite.domain.repository

import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin

interface AuthenticationRepository {

    suspend fun loginBy(param: ApiReqLogin): ApiResLogin
}