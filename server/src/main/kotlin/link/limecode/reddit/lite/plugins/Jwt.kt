package link.limecode.reddit.lite.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import link.limecode.reddit.lite.config.Constants

fun Application.configureJwt() {
    install(Authentication) {
        jwt(Constants.JWT_USER_AUTH) {
            realm = Constants.JWT_REALM
            verifier(
                JWT.require(Algorithm.HMAC256(Constants.JWT_SECRET))
                    .withAudience(Constants.JWT_AUDIENCE)
                    .withIssuer(Constants.JWT_ISSUER)
                    .build()
            )
            
            validate { credential ->
                if (credential.payload.getClaim(Constants.JWT_CLAIM_USERNAME).asString() != null) {
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