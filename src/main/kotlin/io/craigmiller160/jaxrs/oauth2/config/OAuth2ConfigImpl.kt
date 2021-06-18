package io.craigmiller160.jaxrs.oauth2.config

import io.craigmiller160.oauth2.config.OAuth2Config
import java.lang.RuntimeException
import java.util.*

// TODO how do I expose this via hk2 injection? Is it possible?
class OAuth2ConfigImpl : OAuth2Config {
    companion object {
        const val AUTH_CODE_REDIRECT_URI = "oauth2.auth-code-redirect-uri"
        const val AUTH_CODE_WAIT_MINS = "oauth2.auth-code-wait-mins"
        const val AUTH_LOGIN_BASE_URI = "oauth2.auth-login-base-uri"
        const val AUTH_SERVER_HOST = "oauth2.auth-server-host"
        const val CLIENT_KEY = "oauth2.client-key"
        const val CLIENT_SECRET = "oauth2.client-secret"
        const val CLIENT_NAME = "oauth2.client-name"
        const val COOKIE_MAX_AGE_SECS = "oauth2.cookie-max-age-secs"
        const val COOKIE_NAME = "oauth2.cookie-name"
        const val COOKIE_PATH = "oauth2.cookie-path"
        const val INSECURE_PATHS = "oauth2.insecure-paths"
        const val POST_AUTH_REDIRECT = "oauth2.post-auth-redirect"

        private const val PROPS_PATH = "oauth2.properties"

    }

    // TODO need special injection binder class here

    private val props = Properties()

    init {
        // TODO need to validate resource exists
        props.load(javaClass.classLoader.getResourceAsStream(PROPS_PATH))
    }

    override var authCodeRedirectUri: String = props.getProperty(AUTH_CODE_REDIRECT_URI, "")
    override var authCodeWaitMins: Long = props.getProperty(AUTH_CODE_WAIT_MINS, "0").toLong()
    override var authLoginBaseUri: String = props.getProperty(AUTH_LOGIN_BASE_URI)
    override var authServerHost: String = props.getProperty(AUTH_SERVER_HOST)
    override var clientKey: String = props.getProperty(CLIENT_KEY, "")
    override var clientName: String = props.getProperty(CLIENT_NAME, "")
    override var clientSecret: String = props.getProperty(CLIENT_SECRET, "")
    override var cookieMaxAgeSecs: Long = props.getProperty(COOKIE_MAX_AGE_SECS, "0").toLong()
    override var cookieName: String = props.getProperty(COOKIE_NAME, "")
    override var cookiePath: String = props.getProperty(COOKIE_PATH, "")
    override var insecurePaths: String = props.getProperty(INSECURE_PATHS, "")
    override var postAuthRedirect: String = props.getProperty(POST_AUTH_REDIRECT, "")
}