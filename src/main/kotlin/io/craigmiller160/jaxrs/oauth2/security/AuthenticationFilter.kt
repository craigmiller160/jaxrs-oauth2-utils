package io.craigmiller160.jaxrs.oauth2.security

import io.craigmiller160.oauth2.security.AuthenticationFilterService
import io.craigmiller160.oauth2.security.RequestWrapper
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

@Service
class AuthenticationFilter @Inject constructor(
        private val authenticationFilterService: AuthenticationFilterService
) : ContainerRequestFilter, ContainerResponseFilter {

    override fun filter(req: ContainerRequestContext?) {
        authenticationFilterService.authenticateRequest(RequestWrapper(
                requestUri = req?.uriInfo?.path!!,
                getCookieValue = { cookieName ->

                },
                getHeaderValue = { headerName ->

                },
                setAuthentication = { claims ->

                },
                setNewTokenCookie = { cookie ->

                }
        ))
    }

    override fun filter(req: ContainerRequestContext?, res: ContainerResponseContext?) {
        TODO("Not yet implemented")
    }
}