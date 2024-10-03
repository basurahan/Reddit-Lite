package link.limecode.reddit.lite.backend.routes.subreddit.handlers

import link.limecode.reddit.lite.backend.domain.dao.SubRedditDao
import link.limecode.reddit.lite.backend.domain.dao.SubRedditUsersDao
import link.limecode.reddit.lite.backend.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubRedditUser
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubRedditValidation
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubreddit
import link.limecode.reddit.lite.data.model.response.subreddit.isValid

suspend fun ApiReqNewSubReddit?.handleNewSubReddit(
    subRedditDao: SubRedditDao,
    subRedditUsersDao: SubRedditUsersDao,
    userId: Long
): ApiResNewSubreddit {
    val validationResult = validate(subRedditDao)

    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()

    val subReddit = subRedditDao.insert(this)
    subRedditUsersDao.insert(ApiReqNewSubRedditUser(subredditId = subReddit.id, userId = userId))

    return ApiResNewSubreddit.Success(
        subReddit = subReddit
    )
}

suspend fun ApiReqNewSubReddit?.validate(subRedditDao: SubRedditDao): ApiResNewSubreddit.Fail? {
    var formValidation = ApiResNewSubRedditValidation()

    if (this?.name.isNullOrBlank()) formValidation = formValidation.copy(name = "name is required")

    if (!formValidation.isValid())
        return ApiResNewSubreddit.Fail(formValidation)

    if (this == null) throw UnexpectedDataException()

    val result = subRedditDao.getSubRedditByName(name)

    if (result != null) {
        formValidation = formValidation.copy(name = "name already in used")
        return ApiResNewSubreddit.Fail(formValidation)
    }

    return null
}