package link.limecode.reddit.lite.domain.dao

import io.github.jan.supabase.postgrest.query.Order
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqPostListSort

interface PostDao {
    
    suspend fun insert(post: ApiReqNewPost): ApiPost
    suspend fun incrementUpVote(id: Int, currentCount: Int): ApiPost
    suspend fun decrementUpVote(id: Int, currentCount: Int): ApiPost
    suspend fun incrementDownVote(id: Int, currentCount: Int): ApiPost
    suspend fun decrementDownVote(id: Int, currentCount: Int): ApiPost
    suspend fun getUpVoteCount(id: Int): Int
    suspend fun getDownVoteCount(id: Int): Int
    suspend fun getPostBy(id: Int): ApiPost
    suspend fun getPostList(cursor: Int?, limit: Long, order: Order): List<ApiPost>
    suspend fun getPostListBy(subredditId: Int, cursor: Int?, limit: Long, order: Order): List<ApiPost>
}