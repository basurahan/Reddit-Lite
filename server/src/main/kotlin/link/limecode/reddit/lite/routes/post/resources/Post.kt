import io.ktor.resources.*
import kotlinx.serialization.Serializable
import link.limecode.reddit.lite.BaseRoute

@Serializable
@Resource("/post")
class Post(val parent: BaseRoute = BaseRoute()) {
    
    @Serializable
    @Resource("new")
    class New(val parent: Post = Post())
}