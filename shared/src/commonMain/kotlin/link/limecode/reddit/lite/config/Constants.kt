package link.limecode.reddit.lite.config

// TODO: This are going to be migrated to BuildKonfig in the future
object Constants {
    val SERVER_PORT = 8080
    val SUPABASE_URL = "https://eviqhjaawdufedafxusz.supabase.co"
    val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV2aXFoamFhd2R1ZmVkYWZ4dXN6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjQ5Nzk3MDYsImV4cCI6MjA0MDU1NTcwNn0.jbU35EQCRtrVFPzEahZXWdeEq_HlMnJNuGELGiZ_MzA"
    val JWT_USER_AUTH = "jwt-user-auth"
    val JWT_SECRET = "renz pogi"
    val JWT_ISSUER = "http://0.0.0.0:8080/"
    val JWT_AUDIENCE = "http://0.0.0.0:8080/reddit-lite"
    val JWT_REALM = "access-to-reddit-lite"
    val JWT_CLAIM_USERNAME = "username"
    val JWT_CLAIM_USER_ID = "user_id"
    val FALLBACK_ERROR_MESSAGE = "something went wrong"
    val CLIENT_BASE_URL = "http://192.168.1.168:8080/api/v1/"
}