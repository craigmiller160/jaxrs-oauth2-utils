package io.craigmiller160.jaxrs.oauth2.exception

import io.craigmiller160.jaxrs.oauth2.dto.ErrorDto
import io.craigmiller160.oauth2.exception.OAuth2Exception
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class OAuth2ExceptionMapper : ExceptionMapper<OAuth2Exception> {

    companion object {
        fun createErrorResponse(ex: Throwable): Response {
            val status = when (ex) {
                is OAuth2Exception -> ex.statusCode
                else -> 500
            }

            val error = ErrorDto(
                    status = status,
                    message = getMessage(ex)
            )

            return Response
                    .status(status)
                    .entity(error)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build()
        }

        private fun getMessage(ex: Throwable): String {
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

    override fun toResponse(ex: OAuth2Exception): Response {
        return createErrorResponse(ex)
    }
}