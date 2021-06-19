package io.craigmiller160.jaxrs.oauth2.binding

import io.craigmiller160.jaxrs.oauth2.config.OAuth2ConfigImpl
import io.craigmiller160.oauth2.config.OAuth2Config
import org.glassfish.hk2.utilities.binding.AbstractBinder
import javax.ws.rs.ext.Provider

@Provider
class OAuth2InjectionBinder : AbstractBinder() {
    override fun configure() {
        // TODO OAuth2Config is an abstract class, not an interface... is that a problem?
        bind(OAuth2ConfigImpl::class.java)
                .to(OAuth2Config::class.java)
    }
}