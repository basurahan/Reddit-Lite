package link.limecode.reddit.lite.routes.subreddit.handlers

import link.limecode.reddit.lite.data.model.request.subreddit.ApiReqNewSubReddit
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubRedditValidation
import link.limecode.reddit.lite.data.model.response.subreddit.ApiResNewSubreddit
import link.limecode.reddit.lite.data.model.response.subreddit.isValid
import link.limecode.reddit.lite.domain.dao.SubRedditDao
import link.limecode.reddit.lite.exceptions.UnexpectedDataException

suspend fun ApiReqNewSubReddit?.handleNewSubReddit(
    subRedditDao: SubRedditDao
): ApiResNewSubreddit {
    val validationResult = validate(subRedditDao)
    
    if (validationResult != null) return validationResult
    if (this == null) throw UnexpectedDataException()
    
    val subReddit = subRedditDao.insert(this)
    
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