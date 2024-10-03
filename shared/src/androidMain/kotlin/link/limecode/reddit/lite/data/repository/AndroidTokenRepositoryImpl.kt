package link.limecode.reddit.lite.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import link.limecode.reddit.lite.domain.repository.TokenRepository

class AndroidTokenRepositoryImpl(private val context: Context) : TokenRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token_repository")
    private val userTokenKey = stringPreferencesKey("user_token")

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { repo ->
            repo[userTokenKey] = token
        }
    }

    override suspend fun getToken(): String? {
        return context.dataStore.data.map { prefs -> prefs[userTokenKey] }.first()
    }
}