package link.limecode.reddit.lite.config

import link.limecode.config.BuildKonfig

object Constants {
    val SERVER_PORT = BuildKonfig.SERVER_PORT
    val SUPABASE_URL = BuildKonfig.SUPABASE_URL
    val SUPABASE_KEY = BuildKonfig.SUPABASE_KEY
    val JWT_USER_AUTH = BuildKonfig.JWT_USER_AUTH
    val JWT_SECRET = BuildKonfig.JWT_SECRET
    val JWT_ISSUER = BuildKonfig.JWT_ISSUER
    val JWT_AUDIENCE = BuildKonfig.JWT_AUDIENCE
    val JWT_REALM = BuildKonfig.JWT_REALM
    val JWT_CLAIM_USERNAME = BuildKonfig.JWT_CLAIM_USERNAME
    val JWT_CLAIM_USER_ID = BuildKonfig.JWT_CLAIM_USER_ID
    val FALLBACK_ERROR_MESSAGE = BuildKonfig.FALLBACK_ERROR_MESSAGE
}