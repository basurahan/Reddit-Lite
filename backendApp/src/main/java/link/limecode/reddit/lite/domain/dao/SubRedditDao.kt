package link.limecode.reddit.lite.domain.dao

import link.limecode.reddit.lite.data.model.ApiSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit

interface SubRedditDao {
    
    suspend fun getSubRedditByName(name: String): ApiSubReddit?
    
    suspend fun insert(subReddit: ApiReqNewSubReddit): ApiSubReddit
}