package io.craigmiller160.jaxrs.oauth2.config

import io.craigmiller160.jaxrs.oauth2.exception.OAuth2PropertiesException
import io.craigmiller160.oauth2.config.AbstractOAuth2Config
import org.jvnet.hk2.annotations.Service
import java.util.*

@Service
class OAuth2ConfigImpl(propsPath: String) : AbstractOAuth2Config() {
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

        private const val DEFAULT_PROPS_PATH = "oauth2.properties"
    }

    private val props = Properties()

    constructor() : this(DEFAULT_PROPS_PATH)

    init {
        javaClass.classLoader.getResourceAsStream(propsPath)
                ?.let { props.load(it) }
                ?: throw OAuth2PropertiesException("OAuth2 Properties not found")

        System.getenv().forEach { entry ->
            props.setProperty(entry.key, entry.value)
        }
    }

    override var authCodeRedirectUri: String = props.getProperty(AUTH_CODE_REDIRECT_URI, "")
    override var authCodeWaitMins: Long = props.getProperty(AUTH_CODE_WAIT_MINS, "0").toLong()
    override var authLoginBaseUri: String = props.getProperty(AUTH_LOGIN_BASE_URI, "")
    override var authServerHost: String = props.getProperty(AUTH_SERVER_HOST, "")
    override var clientKey: String = props.getProperty(CLIENT_KEY, "")
    override var clientName: String = props.getProperty(CLIENT_NAME, "")
    override var clientSecret: String = props.getProperty(CLIENT_SECRET, "")
    override var cookieMaxAgeSecs: Long = props.getProperty(COOKIE_MAX_AGE_SECS, "0").toLong()
    override var cookieName: String = props.getProperty(COOKIE_NAME, "")
    override var cookiePath: String = props.getProperty(COOKIE_PATH, "")
    override var insecurePaths: String = props.getProperty(INSECURE_PATHS, "")
    override var postAuthRedirect: String = props.getProperty(POST_AUTH_REDIRECT, "")
}