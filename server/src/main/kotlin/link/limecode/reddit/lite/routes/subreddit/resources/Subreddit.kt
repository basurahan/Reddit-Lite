package link.limecode.reddit.lite.routes.subreddit.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("subreddit")
@Suppress("unused")
class Subreddit(val parent: BaseRoute = BaseRoute()) {
    
    @Serializable
    @Resource("new")
    class New(val parent: Subreddit = Subreddit())
}