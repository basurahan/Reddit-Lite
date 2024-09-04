package link.limecode.reddit.lite.data.db.subreddits

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.data.model.ApiSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.domain.dao.SubRedditDao

class SubRedditDaoImpl(private val table: PostgrestQueryBuilder) : SubRedditDao {
    
    override suspend fun getSubRedditByName(name: String): ApiSubReddit? {
        return table.select{
            filter {
                ApiSubReddit::name eq name
            }
        }.decodeSingleOrNull<ApiSubReddit>()
    }

    override suspend fun insert(subReddit: ApiReqNewSubReddit): ApiSubReddit {
        return table.insert(subReddit) { select() }.decodeSingle()
    }
}