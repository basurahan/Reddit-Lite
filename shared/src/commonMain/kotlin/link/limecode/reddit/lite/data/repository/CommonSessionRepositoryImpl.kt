package link.limecode.reddit.lite.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import link.limecode.reddit.lite.database.SessionQueries
import link.limecode.reddit.lite.domain.model.UiUser
import link.limecode.reddit.lite.domain.repository.SessionRepository
import link.limecode.reddit.lite.util.getCurrentDateTime

class CommonSessionRepositoryImpl(private val sessionQueries: SessionQueries) : SessionRepository {

    override suspend fun setSessionBy(user: UiUser, token: String) {
        withContext(Dispatchers.IO) {
            sessionQueries.setSessionBy(
                token = token,
                email = user.email,
                username = user.username,
                updated_at = getCurrentDateTime()
            )
        }
    }

    override fun getToken(): Flow<String?> {
        return sessionQueries.getToken().asFlow().mapToOneOrNull(Dispatchers.IO)
    }
}