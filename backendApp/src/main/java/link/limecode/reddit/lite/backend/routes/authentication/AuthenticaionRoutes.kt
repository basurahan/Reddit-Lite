package link.limecode.reddit.lite.backend.routes.authentication

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.backend.domain.dao.UsersDao
import link.limecode.reddit.lite.backend.domain.usecase.AuthUseCase
import link.limecode.reddit.lite.backend.routes.authentication.handlers.loginHandler
import link.limecode.reddit.lite.backend.routes.authentication.handlers.registerHandler
import link.limecode.reddit.lite.backend.routes.authentication.resources.Login
import link.limecode.reddit.lite.backend.routes.authentication.resources.Register
import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.model.response.register.ApiResRegister
import org.koin.ktor.plugin.scope

fun Route.configureAuthenticationRoutes() {

    post<Register> {
        val usersDao = call.scope.get<UsersDao>()
        val authUseCase = call.scope.get<AuthUseCase>()

        val requestData = runCatching { call.receiveNullable<ApiReqRegister>() }.getOrNull()
        when (val result =
            requestData.registerHandler(usersDao = usersDao, authUseCase = authUseCase)) {
            is ApiResRegister.Fail -> call.respond(
                status = HttpStatusCode.UnprocessableEntity,
                message = result
            )

            is ApiResRegister.Success -> call.respond(result)
        }
    }

    post<Login> {
        val authUseCase = call.scope.get<AuthUseCase>()

        val requestData = runCatching { call.receiveNullable<ApiReqLogin>() }.getOrNull()
        val result = requestData.loginHandler(authUseCase)
        val status = when (result) {
            is ApiResLogin.Fail -> HttpStatusCode.UnprocessableEntity
            is ApiResLogin.Success -> HttpStatusCode.OK
        }

        call.respond(status = status, message = result)
    }
}