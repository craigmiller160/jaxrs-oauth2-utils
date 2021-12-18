package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.security.AuthConstants
import io.craigmiller160.oauth2.provider.AuthUserProvider
import io.craigmiller160.oauth2.security.AuthenticatedUser
import jakarta.inject.Inject
import jakarta.inject.Provider
import jakarta.ws.rs.container.ContainerRequestContext
import org.glassfish.hk2.api.Factory

class AuthUserProviderFactory @Inject constructor(
        private val reqContextProvider: Provider<ContainerRequestContext>
) : Factory<AuthUserProvider> {

    override fun provide(): AuthUserProvider = AuthUserProvider {
        reqContextProvider.get().getProperty(AuthConstants.AUTHENTICATED_USER) as AuthenticatedUser
    }

    override fun dispose(provider: AuthUserProvider?) {}
}