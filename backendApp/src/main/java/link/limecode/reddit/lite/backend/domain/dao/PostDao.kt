package link.limecode.reddit.lite.backend.domain.dao

import io.github.jan.supabase.postgrest.query.Order
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost

interface PostDao {

    suspend fun insert(post: ApiReqNewPost): ApiPost
    suspend fun incrementUpVote(id: Long, currentCount: Int): ApiPost
    suspend fun decrementUpVote(id: Long, currentCount: Int): ApiPost
    suspend fun incrementDownVote(id: Long, currentCount: Int): ApiPost
    suspend fun decrementDownVote(id: Long, currentCount: Int): ApiPost
    suspend fun getUpVoteCount(id: Long): Int
    suspend fun getDownVoteCount(id: Long): Int
    suspend fun getPostBy(id: Long): ApiPost
    suspend fun getPostList(cursor: Long?, limit: Long, order: Order): List<ApiPost>
    suspend fun getPostListBy(
        subredditId: Long,
        cursor: Long?,
        limit: Long,
        order: Order
    ): List<ApiPost>
}