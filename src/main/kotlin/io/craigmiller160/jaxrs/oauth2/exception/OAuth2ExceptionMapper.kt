package io.craigmiller160.jaxrs.oauth2.exception

import io.craigmiller160.jaxrs.oauth2.dto.ErrorDto
import io.craigmiller160.oauth2.exception.OAuth2Exception
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class OAuth2ExceptionMapper : ExceptionMapper<OAuth2Exception> {
    override fun toResponse(ex: OAuth2Exception): Response {
        val error = ErrorDto(
                status = ex.statusCode,
                message = getMessage(ex)
        )

        return Response
                .status(ex.statusCode)
                .entity(error)
                .encoding(MediaType.APPLICATION_JSON)
                .build()
    }

    private fun getMessage(ex: OAuth2Exception): String {
        var message = ex.message ?: ""
        var cause = ex.cause
        while (cause != null) {
            message = cause.message
                    ?.let { "$message - $it" }
                    ?: message
            cause = cause.cause
        }
        return message
    }
}