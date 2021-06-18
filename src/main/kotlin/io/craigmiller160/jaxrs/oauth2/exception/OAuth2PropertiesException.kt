package io.craigmiller160.jaxrs.oauth2.exception

import java.lang.RuntimeException

class OAuth2PropertiesException(msg: String, cause: Throwable? = null) : RuntimeException(msg, cause)