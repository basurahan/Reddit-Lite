package link.limecode.reddit.lite.client

interface Platform {
    val target: Target
}

expect fun getPlatform(): Platform