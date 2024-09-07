package link.limecode.reddit.lite.routes.authentication.handlers

import link.limecode.reddit.lite.domain.dao.UsersDao
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister
import link.limecode.reddit.lite.data.model.response.register.ApiResRegister
import link.limecode.reddit.lite.data.model.response.register.ApiResRegisterValidation
import link.limecode.reddit.lite.data.model.response.register.isValid
import link.limecode.reddit.lite.domain.usecase.AuthUsecase
import link.limecode.reddit.lite.exceptions.UnexpectedDataException
import link.limecode.reddit.lite.util.isValidEmail

suspend fun ApiReqRegister?.registerHandler(
    usersDao: UsersDao,
    authUsecase: AuthUsecase
): ApiResRegister {

    val validationResult = validate(usersDao)
    if (validationResult != null) return validationResult

    if (this == null) throw UnexpectedDataException()

    // insert data
    val user = authUsecase.register(this)

    return ApiResRegister.Success(
        user = user,
        message = "Registration successful"
    )
}

suspend fun ApiReqRegister?.validate(usersDao: UsersDao): ApiResRegister.Fail? {
    var formValidation = ApiResRegisterValidation()

    if (this?.email.isNullOrBlank()) formValidation = formValidation.copy(email = "email is required")
    if (this?.username.isNullOrBlank()) formValidation = formValidation.copy(username = "username is required")
    if (this?.password.isNullOrBlank()) formValidation = formValidation.copy(password = "password is required")

    if (!formValidation.isValid())
        return ApiResRegister.Fail(formValidation)

    if (this == null) throw UnexpectedDataException()

    // check if email already in used
    val emailResult = usersDao.getUserByEmail(email)
    if (emailResult != null) formValidation = formValidation.copy(email = "email already in used")

    val usernameResult = usersDao.getUserByUsername(username)
    if (usernameResult != null) formValidation = formValidation.copy(username = "username already in used")

    if (!formValidation.isValid())
        return ApiResRegister.Fail(formValidation)

    if (!email.isValidEmail()) formValidation = formValidation.copy(email = "invalid email format")

    if (!formValidation.isValid())
        return ApiResRegister.Fail(formValidation)

    return null
}