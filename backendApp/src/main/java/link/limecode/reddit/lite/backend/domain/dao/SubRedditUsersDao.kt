package link.limecode.reddit.lite.backend.domain.dao

import link.limecode.reddit.lite.data.model.ApiSubRedditUser
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubRedditUser

interface SubRedditUsersDao {

    suspend fun insert(entry: ApiReqNewSubRedditUser): ApiSubRedditUser
    suspend fun getSubRedditUserBy(subRedditId: Long, userId: Long): ApiSubRedditUser?
}