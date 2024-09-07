package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost

interface PostDao {
    
    suspend fun insert(post: ApiReqNewPost): ApiPost
}