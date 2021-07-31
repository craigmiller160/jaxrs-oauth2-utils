package io.craigmiller160.jaxrs.oauth2.exception

import io.craigmiller160.oauth2.exception.OAuth2Exception
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class OAuth2ExceptionMapper : ExceptionMapper<OAuth2Exception> {
    override fun toResponse(ex: OAuth2Exception?): Response {
        TODO("Not yet implemented")
    }
}