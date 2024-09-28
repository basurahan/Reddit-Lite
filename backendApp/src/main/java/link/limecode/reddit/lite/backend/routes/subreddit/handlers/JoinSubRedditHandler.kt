package link.limecode.reddit.lite.backend.routes.subreddit.handlers

import link.limecode.reddit.lite.backend.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.backend.exceptions.AlreadyJoinedException
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqJoinSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubRedditUser

suspend fun ApiReqJoinSubReddit?.handleJoinSubReddit(
    subRedditUsersDao: SubRedditUsersDao,
    userId: Long
) {
    if (this == null) throw UnexpectedDataException()

    val records = subRedditUsersDao.getSubRedditUserBy(
        subRedditId = subRedditId,
        userId = userId
    )
    if (records != null) throw AlreadyJoinedException()

    subRedditUsersDao.insert(
        ApiReqNewSubRedditUser(
            subredditId = subRedditId,
            userId = userId
        )
    )
}