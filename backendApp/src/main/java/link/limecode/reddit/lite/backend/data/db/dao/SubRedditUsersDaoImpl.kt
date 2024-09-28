package link.limecode.reddit.lite.backend.data.db.dao

import io.github.jan.supabase.postgrest.query.PostgrestQueryBuilder
import link.limecode.reddit.lite.backend.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.data.model.ApiSubRedditUser
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubRedditUser

class SubRedditUsersDaoImpl(private val table: PostgrestQueryBuilder) : SubRedditUsersDao {

    override suspend fun insert(entry: ApiReqNewSubRedditUser): ApiSubRedditUser {
        return table.insert(entry) {
            select()
        }.decodeSingle<ApiSubRedditUser>()
    }

    override suspend fun getSubRedditUserBy(subRedditId: Long, userId: Long): ApiSubRedditUser? {
        return table.select {
            filter {
                ApiSubRedditUser::subredditId eq subRedditId
                ApiSubRedditUser::userId eq userId
            }
        }.decodeSingleOrNull<ApiSubRedditUser>()
    }
}