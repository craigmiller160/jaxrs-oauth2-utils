package io.craigmiller160.jaxrs.oauth2.security

import io.craigmiller160.oauth2.security.AuthenticationFilterService
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

class AuthenticationFilter(
        private val authenticationFilterService: AuthenticationFilterService
) : ContainerRequestFilter, ContainerResponseFilter {
    override fun filter(req: ContainerRequestContext?) {
        TODO("Not yet implemented")
    }

    override fun filter(req: ContainerRequestContext?, res: ContainerResponseContext?) {
        TODO("Not yet implemented")
    }
}