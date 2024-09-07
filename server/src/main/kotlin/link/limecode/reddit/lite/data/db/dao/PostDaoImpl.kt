package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiPost
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPost
import link.limecode.reddit.lite.domain.dao.PostDao

class PostDaoImpl(private val table: PostgrestQueryBuilder) : PostDao {
    
    override suspend fun insert(post: ApiReqNewPost): ApiPost {
        return table.insert(post) { select() }.decodeSingle<ApiPost>()
    }
}