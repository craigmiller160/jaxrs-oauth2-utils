package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.security.AuthConstants
import io.craigmiller160.oauth2.security.AuthenticatedUser
import io.craigmiller160.oauth2.service.AuthUserProvider
import org.glassfish.hk2.api.Factory
import javax.inject.Inject
import javax.inject.Provider
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.core.Context

class AuthUserProviderFactory @Inject constructor(
        private val reqContextProvider: Provider<ContainerRequestContext>
) : Factory<AuthUserProvider> {

    override fun provide(): AuthUserProvider = AuthUserProvider {
        reqContextProvider.get().getProperty(AuthConstants.AUTHENTICATED_USER) as AuthenticatedUser
    }

    override fun dispose(provider: AuthUserProvider?) {}
}