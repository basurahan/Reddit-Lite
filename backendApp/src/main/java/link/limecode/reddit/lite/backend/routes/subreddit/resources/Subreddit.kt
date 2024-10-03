package link.limecode.reddit.lite.backend.routes.subreddit.resources

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.backend.BaseRoute

@Serializable
@Resource("subreddit")
@Suppress("unused")
class Subreddit(val parent: BaseRoute = BaseRoute()) {

    @Serializable
    @Resource("new")
    class New(val parent: Subreddit = Subreddit())

    @Serializable
    @Resource("join")
    class Join(val parent: Subreddit = Subreddit())
}