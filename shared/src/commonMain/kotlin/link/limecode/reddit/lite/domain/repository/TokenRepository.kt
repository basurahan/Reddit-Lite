package link.limecode.reddit.lite.domain.repository

interface TokenRepository {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}