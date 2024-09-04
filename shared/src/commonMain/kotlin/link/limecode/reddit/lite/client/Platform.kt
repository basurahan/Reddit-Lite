package link.limecode.reddit.lite.client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform