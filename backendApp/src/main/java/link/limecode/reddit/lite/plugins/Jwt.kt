package link.limecode.reddit.lite.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import link.limecode.reddit.lite.config.CommonConstants

fun Application.configureJwt() {
    install(Authentication) {
        jwt(CommonConstants.JWT_USER_AUTH) {
            realm = CommonConstants.JWT_REALM
            verifier(
                JWT.require(Algorithm.HMAC256(CommonConstants.JWT_SECRET))
                    .withAudience(CommonConstants.JWT_AUDIENCE)
                    .withIssuer(CommonConstants.JWT_ISSUER)
                    .build()
            )
            
            validate { credential ->
                val username: String? = credential.payload.getClaim(CommonConstants.JWT_CLAIM_USERNAME).asString()
                val userId: Int? = credential.payload.getClaim(CommonConstants.JWT_CLAIM_USER_ID).asInt()

                if (userId != null && username != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            
            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = "no access"
                )
            }
        }
    }
}