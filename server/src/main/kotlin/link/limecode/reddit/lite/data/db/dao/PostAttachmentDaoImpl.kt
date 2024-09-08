package link.limecode.reddit.lite.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiPostAttachment
import link.limecode.reddit.lite.data.model.request.post.ApiReqNewPostAttachement
import link.limecode.reddit.lite.domain.dao.PostAttachementDao

class PostAttachmentDaoImpl(private val table: PostgrestQueryBuilder) : PostAttachementDao {
    
    override suspend fun insert(attachment: ApiReqNewPostAttachement): ApiPostAttachment {
        return table.insert(attachment){ select() }.decodeSingle<ApiPostAttachment>()
    }
}