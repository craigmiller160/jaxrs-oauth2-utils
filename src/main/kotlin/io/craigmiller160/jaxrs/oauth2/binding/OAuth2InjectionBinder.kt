package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.client.AuthServerClientInjectImpl
import io.craigmiller160.jaxrs.oauth2.config.OAuth2ConfigImpl
import io.craigmiller160.jaxrs.oauth2.domain.AppRefreshTokenRepositoryInjectImpl
import io.craigmiller160.jaxrs.oauth2.service.AuthCodeServiceInjectImpl
import io.craigmiller160.jaxrs.oauth2.service.AuthenticationFilterServiceInjectImpl
import io.craigmiller160.jaxrs.oauth2.service.OAuth2ServiceInjectImpl
import io.craigmiller160.jaxrs.oauth2.service.RefreshTokenServiceInjectImpl
import io.craigmiller160.oauth2.client.AuthServerClient
import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.provider.AuthUserProvider
import io.craigmiller160.oauth2.security.AuthenticationFilterService
import io.craigmiller160.oauth2.security.CookieCreator
import io.craigmiller160.oauth2.service.AuthCodeService
import io.craigmiller160.oauth2.service.OAuth2Service
import io.craigmiller160.oauth2.service.RefreshTokenService
import jakarta.inject.Singleton
import jakarta.ws.rs.ext.Provider
import org.glassfish.hk2.utilities.binding.AbstractBinder

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
        bind(AppRefreshTokenRepositoryInjectImpl::class.java)
                .to(AppRefreshTokenRepository::class.java)
                .`in`(Singleton::class.java)
        bind(OAuth2ServiceInjectImpl::class.java)
                .to(OAuth2Service::class.java)
                .`in`(Singleton::class.java)
        bind(RefreshTokenServiceInjectImpl::class.java)
                .to(RefreshTokenService::class.java)
                .`in`(Singleton::class.java)
        bind(AuthCodeServiceInjectImpl::class.java)
                .to(AuthCodeService::class.java)
                .`in`(Singleton::class.java)
        bind(AuthenticationFilterServiceInjectImpl::class.java)
                .to(AuthenticationFilterService::class.java)
                .`in`(Singleton::class.java)

        bindFactory(AuthUserProviderFactory::class.java)
                .to(AuthUserProvider::class.java)
    }
}