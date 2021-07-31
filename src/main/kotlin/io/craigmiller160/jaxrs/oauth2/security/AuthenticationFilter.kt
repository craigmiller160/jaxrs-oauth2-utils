package io.craigmiller160.jaxrs.oauth2.security

import com.nimbusds.jwt.JWTClaimsSet
import io.craigmiller160.jaxrs.oauth2.exception.OAuth2ExceptionMapper
import io.craigmiller160.oauth2.security.AuthenticatedUser
import io.craigmiller160.oauth2.security.AuthenticationFilterService
import io.craigmiller160.oauth2.security.RequestWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.ext.Provider

@Provider
class AuthenticationFilter @Inject constructor(
        private val authenticationFilterService: AuthenticationFilterService
) : ContainerRequestFilter, ContainerResponseFilter {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private fun ensureLeadingSlash(path: String): String =
            when {
                path.startsWith("/") -> path
                else -> "/$path"
            }

    override fun filter(req: ContainerRequestContext) {
        authenticationFilterService.authenticateRequest(RequestWrapper(
                requestUri = ensureLeadingSlash(req.uriInfo.path),
                getCookieValue = { cookieName -> req.cookies[cookieName]?.value },
                getHeaderValue = { headerName -> req.getHeaderString(headerName) },
                setAuthentication = { claims ->
                    val authentication = createAuthenticatedUser(claims)
                    req.setProperty(AuthConstants.AUTHENTICATED_USER, authentication)
                },
                setNewTokenCookie = { cookie ->
                    req.setProperty(AuthConstants.NEW_TOKEN_FOR_COOKIE, cookie)
                }
        ))
                .onFailure { ex ->
                    logger.error("Token validation failed: ${ex.message}")
                    logger.debug("", ex)
                    val error = OAuth2ExceptionMapper.createErrorResponse(ex)
                    req.abortWith(error)
                }
    }

    private fun createAuthenticatedUser(claims: JWTClaimsSet): AuthenticatedUser =
            AuthenticatedUserImpl(
                    userName = claims.subject,
                    roles = claims.getStringListClaim("roles"),
                    firstName = claims.getStringClaim("firstName"),
                    lastName = claims.getStringClaim("lastName"),
                    tokenId = claims.jwtid
            )

    override fun filter(req: ContainerRequestContext, res: ContainerResponseContext) {
        req.getProperty(AuthConstants.NEW_TOKEN_FOR_COOKIE)
                ?.let { cookie -> res.headers.add("Set-Cookie", cookie) }
    }
}