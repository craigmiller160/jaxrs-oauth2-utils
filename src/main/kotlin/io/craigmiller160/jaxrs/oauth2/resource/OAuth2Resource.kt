package io.craigmiller160.jaxrs.oauth2.resource

import io.craigmiller160.oauth2.dto.AuthCodeLoginDto
import io.craigmiller160.oauth2.resource.ResourceConstants
import io.craigmiller160.oauth2.service.AuthCodeService
import io.craigmiller160.oauth2.service.OAuth2Service
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response

@Path(ResourceConstants.ROOT_PATH)
class OAuth2Resource @Inject constructor(
        private val authCodeService: AuthCodeService,
        private val oAuthService: OAuth2Service
) {
    @Context
    lateinit var req: HttpServletRequest

    @POST
    @Path(ResourceConstants.AUTHCODE_LOGIN_PATH)
    fun login(): Response {
        val authCodeLoginUrl = authCodeService.prepareAuthCodeLogin(req)
        return Response
                .ok(AuthCodeLoginDto(authCodeLoginUrl))
                .build()
    }

    @GET
    @Path(ResourceConstants.AUTHCODE_CODE_PATH)
    fun code(@QueryParam("code") code: String, @QueryParam("state") state: String): Response {
        val result = authCodeService.code(req, code, state)
        return Response
                .status(302)
                .header("Location", result.postAuthRedirect)
                .header("Set-Cookie", result.cookie)
                .build()
    }

    @GET
    @Path(ResourceConstants.LOGOUT_PATH)
    fun logout(): Response {
        val cookie = oAuthService.logout()
        return Response
                .status(200)
                .header("Set-Cookie", cookie)
                .build()
    }

    @GET
    @Path(ResourceConstants.AUTH_USER_PATH)
    fun getAuthenticatedUser(): Response {
        val authUser = oAuthService.getAuthenticatedUser()
        return Response
                .ok(authUser)
                .build()
    }
}