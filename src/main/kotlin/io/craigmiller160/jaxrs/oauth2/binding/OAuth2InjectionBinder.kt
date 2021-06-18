package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.config.OAuth2ConfigImpl
import io.craigmiller160.oauth2.config.OAuth2Config
import org.glassfish.hk2.utilities.binding.AbstractBinder
import javax.ws.rs.ext.Provider

@Provider
class OAuth2InjectionBinder : AbstractBinder() {
    override fun configure() {
        bind(OAuth2Config::class.java)
                .to(OAuth2ConfigImpl::class.java)
    }
}