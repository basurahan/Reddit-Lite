package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiUser
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister

interface UsersDao {
    
    suspend fun getUserByEmail(email: String): ApiUser?
    
    suspend fun getUserByUsername(username: String): ApiUser?
    
    suspend fun insert(user: ApiReqRegister): ApiUser

    suspend fun getUserByUsernameAndPassword(username: String, password: String): ApiUser?
}
