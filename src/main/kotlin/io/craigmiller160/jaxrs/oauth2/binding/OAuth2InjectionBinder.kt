package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.client.AuthServerClientInjectImpl
import io.craigmiller160.jaxrs.oauth2.config.OAuth2ConfigImpl
import io.craigmiller160.jaxrs.oauth2.domain.repository.JdbcAppRefreshTokenRepository
import io.craigmiller160.jaxrs.oauth2.service.OAuth2ServiceInjectImpl
import io.craigmiller160.oauth2.client.AuthServerClient
import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.security.CookieCreator
import io.craigmiller160.oauth2.service.AuthUserProvider
import io.craigmiller160.oauth2.service.OAuth2Service
import org.glassfish.hk2.utilities.binding.AbstractBinder
import javax.inject.Singleton
import javax.ws.rs.ext.Provider

@Provider
class OAuth2InjectionBinder : AbstractBinder() {
    override fun configure() {
        bindAsContract(CookieCreator::class.java)

        bind(OAuth2ConfigImpl::class.java)
                .to(OAuth2Config::class.java)
                .`in`(Singleton::class.java)
        bind(AuthServerClientInjectImpl::class.java)
                .to(AuthServerClient::class.java)
                .`in`(Singleton::class.java)
        bind(JdbcAppRefreshTokenRepository::class.java)
                .to(AppRefreshTokenRepository::class.java)
                .`in`(Singleton::class.java)
        bind(OAuth2ServiceInjectImpl::class.java)
                .to(OAuth2Service::class.java)
                .`in`(Singleton::class.java)

        bindFactory(AuthUserProviderFactory::class.java)
                .to(AuthUserProvider::class.java)
    }
}