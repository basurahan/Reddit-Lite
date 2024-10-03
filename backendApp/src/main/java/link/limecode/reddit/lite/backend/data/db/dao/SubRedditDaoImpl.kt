package link.limecode.reddit.lite.backend.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.backend.domain.dao.SubRedditDao
import link.limecode.reddit.lite.data.model.ApiSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit

class SubRedditDaoImpl(private val table: PostgrestQueryBuilder) : SubRedditDao {

    override suspend fun getSubRedditByName(name: String): ApiSubReddit? {
        return table.select {
            filter {
                ApiSubReddit::name eq name
            }
        }.decodeSingleOrNull<ApiSubReddit>()
    }

    override suspend fun insert(subReddit: ApiReqNewSubReddit): ApiSubReddit {
        return table.insert(subReddit) { select() }.decodeSingle()
    }
}