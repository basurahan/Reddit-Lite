package link.limecode.reddit.lite.routes.authentication

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import link.limecode.reddit.lite.data.model.request.login.ApiReqLogin
import link.limecode.reddit.lite.data.model.request.register.ApiReqRegister
import link.limecode.reddit.lite.data.model.response.login.ApiResLogin
import link.limecode.reddit.lite.data.model.response.register.ApiResRegister
import link.limecode.reddit.lite.domain.dao.UsersDao
import link.limecode.reddit.lite.domain.usecase.AuthUsecase
import link.limecode.reddit.lite.routes.authentication.handlers.loginHandler
import link.limecode.reddit.lite.routes.authentication.handlers.registerHandler
import link.limecode.reddit.lite.routes.authentication.resources.Login
import link.limecode.reddit.lite.routes.authentication.resources.Register
import org.koin.ktor.plugin.scope

fun Route.configureAuthenticationRoutes() {

    post<Register> {
        val usersDao = call.scope.get<UsersDao>()
        val authUsecase = call.scope.get<AuthUsecase>()

        val requestData = runCatching { call.receiveNullable<ApiReqRegister>() }.getOrNull()
        when (val result = requestData.registerHandler(usersDao = usersDao, authUsecase = authUsecase)) {
            is ApiResRegister.Fail -> call.respond(status = HttpStatusCode.UnprocessableEntity, message = result)
            is ApiResRegister.Success -> call.respond(result)
        }
    }

    post<Login> {
        val authUsecase = call.scope.get<AuthUsecase>()

        val requestData = runCatching { call.receiveNullable<ApiReqLogin>() }.getOrNull()
        val result = requestData.loginHandler(authUsecase)
        val status = when (result) {
            is ApiResLogin.Fail -> HttpStatusCode.UnprocessableEntity
            is ApiResLogin.Success -> HttpStatusCode.OK
        }

        call.respond(status = status, message = result)
    }
}