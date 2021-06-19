package io.craigmiller160.jaxrs.oauth2.config

import io.craigmiller160.jaxrs.oauth2.exception.OAuth2PropertiesException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables
import uk.org.webcompere.systemstubs.jupiter.SystemStub
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExtendWith(SystemStubsExtension::class)
class OAuth2ConfigImplTest {

    @SystemStub
    private lateinit var environmentVariables: EnvironmentVariables

    @Test
    fun test_loadProps() {
        val oauth2Config = OAuth2ConfigImpl()
        assertEquals("redirect-uri", oauth2Config.authCodeRedirectUri)
        assertEquals(1, oauth2Config.authCodeWaitMins)
        assertEquals("login-base-uri", oauth2Config.authLoginBaseUri)
        assertEquals("host", oauth2Config.authServerHost)
        assertEquals("key", oauth2Config.clientKey)
        assertEquals("secret", oauth2Config.clientSecret)
        assertEquals("name", oauth2Config.clientName)
        assertEquals(2, oauth2Config.cookieMaxAgeSecs)
        assertEquals("cookie-name", oauth2Config.cookieName)
        assertEquals("cookie-path", oauth2Config.cookiePath)
        assertEquals("insecure", oauth2Config.insecurePaths)
        assertEquals("post-auth-redirect", oauth2Config.postAuthRedirect)
    }

    @Test
    fun test_loadProps_withEnv() {
        environmentVariables.set(OAuth2ConfigImpl.POST_AUTH_REDIRECT, "bar")
        val oauth2Config = OAuth2ConfigImpl()

        assertEquals("redirect-uri", oauth2Config.authCodeRedirectUri)
        assertEquals(1, oauth2Config.authCodeWaitMins)
        assertEquals("login-base-uri", oauth2Config.authLoginBaseUri)
        assertEquals("host", oauth2Config.authServerHost)
        assertEquals("key", oauth2Config.clientKey)
        assertEquals("secret", oauth2Config.clientSecret)
        assertEquals("name", oauth2Config.clientName)
        assertEquals(2, oauth2Config.cookieMaxAgeSecs)
        assertEquals("cookie-name", oauth2Config.cookieName)
        assertEquals("cookie-path", oauth2Config.cookiePath)
        assertEquals("insecure", oauth2Config.insecurePaths)
        assertEquals("bar", oauth2Config.postAuthRedirect)
    }

    @Test
    fun test_loadProps_fileNotFound() {
        assertFailsWith<OAuth2PropertiesException> {
            OAuth2ConfigImpl("foo.properties")
        }
    }

}