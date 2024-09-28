package link.limecode.reddit.lite.data.db

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import link.limecode.reddit.lite.config.CommonConstants

class Database {
    
    private var _client: SupabaseClient = createSupabaseClient(
        supabaseUrl = CommonConstants.SUPABASE_URL,
        supabaseKey = CommonConstants.SUPABASE_KEY
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