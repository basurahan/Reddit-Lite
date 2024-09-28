package link.limecode.reddit.lite.backend.domain.usecase

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import link.limecode.reddit.lite.backend.domain.dao.UsersDao
import link.limecode.reddit.lite.backend.exceptions.InvalidCredentialException
import link.limecode.reddit.lite.config.CommonConstants
import link.limecode.reddit.lite.data.model.ApiUser
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister
import java.security.MessageDigest
import java.time.ZoneOffset

class AuthUseCase(private val usersDao: UsersDao) {

    suspend fun login(username: String, password: String): Pair<ApiUser, String> {
        val hashedPassword = password.sha256()

        val userData = usersDao.getUserByUsernameAndPassword(
            username = username,
            password = hashedPassword
        )

        if (userData == null) throw InvalidCredentialException()

        val token = generateToken(
            username = userData.username,
            userId = userData.id
        )

        return Pair(userData, token)
    }

    suspend fun register(user: ApiReqRegister): ApiUser {
        val finalUser = user.copy(password = user.password.sha256())
        return usersDao.insert(finalUser)
    }

    private fun generateToken(username: String, userId: Long): String {
        val currentMoment = Clock.System.now()
        val datetimeInUtc = currentMoment.plus(
            value = 24,
            unit = DateTimeUnit.HOUR
        ).toLocalDateTime(TimeZone.UTC).toJavaLocalDateTime()

        val token = JWT.create()
            .withAudience(CommonConstants.JWT_AUDIENCE)
            .withIssuer(CommonConstants.JWT_ISSUER)
            .withClaim(CommonConstants.JWT_CLAIM_USERNAME, username)
            .withClaim(CommonConstants.JWT_CLAIM_USER_ID, userId)
            .withExpiresAt(datetimeInUtc.toInstant(ZoneOffset.UTC))
            .sign(Algorithm.HMAC256(CommonConstants.JWT_SECRET))

        return token
    }

    private fun String.sha256(): String {
        return hashString(
            input = this,
            algorithm = "SHA-256"
        )
    }

    @Suppress("SameParameterValue")
    private fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }
}