package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiUser
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister
import link.limecode.reddit.lite.domain.dao.UsersDao

class UsersDaoImpl (private val table: PostgrestQueryBuilder) : UsersDao {
    
    override suspend fun getUserByEmail(email: String): ApiUser? {
        return table.select {
            filter {
                ApiUser::email eq email
            }
        }.decodeSingleOrNull<ApiUser>()
    }

    override suspend fun getUserByUsername(username: String): ApiUser? {
       return table.select {
           filter {
               ApiUser::username eq username
           }
       }.decodeSingleOrNull<ApiUser>()
    }

    override suspend fun insert(user: ApiReqRegister): ApiUser {
        return table.insert(user) {
            select()
        }.decodeSingle<ApiUser>()
    }

    override suspend fun getUserByUsernameAndPassword(username: String, password: String): ApiUser? {
        return table.select {
            filter {
                ApiUser::username eq username
                ApiUser::password eq password
            }
        }.decodeSingleOrNull<ApiUser>()
    }
}