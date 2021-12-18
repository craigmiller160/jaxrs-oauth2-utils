package io.craigmiller160.jaxrs.oauth2.resource

import io.craigmiller160.oauth2.dto.AuthCodeLoginDto
import io.craigmiller160.oauth2.endpoint.OAuth2Endpoint
import io.craigmiller160.oauth2.endpoint.PathConstants
import io.craigmiller160.oauth2.service.AuthCodeService
import io.craigmiller160.oauth2.service.OAuth2Service
import jakarta.inject.Inject
import jakarta.servlet.http.HttpServletRequest
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.Response

@Path(PathConstants.ROOT_PATH)
class OAuth2Resource @Inject constructor(
        private val authCodeService: AuthCodeService,
        private val oAuthService: OAuth2Service
) : OAuth2Endpoint<Response> {
    @Context
    lateinit var req: HttpServletRequest

    @POST
    @Path(PathConstants.AUTHCODE_LOGIN_PATH)
    override fun login(): Response {
        val authCodeLoginUrl = authCodeService.prepareAuthCodeLogin(req)
        return Response
                .ok(AuthCodeLoginDto(authCodeLoginUrl))
                .build()
    }

    @GET
    @Path(PathConstants.AUTHCODE_CODE_PATH)
    override fun code(@QueryParam("code") code: String, @QueryParam("state") state: String): Response {
        val result = authCodeService.code(req, code, state)
        return Response
                .status(302)
                .header("Location", result.postAuthRedirect)
                .header("Set-Cookie", result.cookie)
                .build()
    }

    @GET
    @Path(PathConstants.LOGOUT_PATH)
    override fun logout(): Response {
        val cookie = oAuthService.logout()
        return Response
                .status(200)
                .header("Set-Cookie", cookie)
                .build()
    }

    @GET
    @Path(PathConstants.AUTH_USER_PATH)
    override fun getAuthenticatedUser(): Response {
        val authUser = oAuthService.getAuthenticatedUser()
        return Response
                .ok(authUser)
                .build()
    }
}