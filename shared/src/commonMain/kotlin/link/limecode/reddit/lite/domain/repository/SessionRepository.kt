package link.limecode.reddit.lite.domain.repository

import kotlinx.coroutines.flow.Flow
import link.limecode.reddit.lite.domain.model.UiUser

interface SessionRepository {
    suspend fun setSessionBy(user: UiUser, token: String)
    fun getToken(): Flow<String?>
}