package link.limecode.reddit.lite.data.db

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import link.limecode.reddit.lite.config.Constants

class Database {
    
    private var _client: SupabaseClient = createSupabaseClient(
        supabaseUrl = Constants.SUPABASE_URL,
        supabaseKey = Constants.SUPABASE_KEY
    ) {
        install(Postgrest)
        defaultSerializer = KotlinXSerializer(
            Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
            }
        )
    }

    val client: SupabaseClient
        get() = _client
}