package io.craigmiller160.jaxrs.oauth2.resource

import io.craigmiller160.jaxrs.oauth2.testutils.JwtUtils
import io.craigmiller160.oauth2.dto.AuthCodeLoginDto
import io.craigmiller160.oauth2.dto.AuthCodeSuccessDto
import io.craigmiller160.oauth2.dto.AuthUserDto
import io.craigmiller160.oauth2.service.AuthCodeService
import io.craigmiller160.oauth2.service.OAuth2Service
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import javax.servlet.http.HttpServletRequest

@ExtendWith(MockitoExtension::class)
class OAuth2ResourceTest {

    companion object {
        private const val AUTH_CODE_LOGIN_URL = "authCodeLoginUrl"
        private const val COOKIE = "cookie"
        private const val POST_AUTH_REDIRECT = "postAuthRedirect"
        private const val CODE = "code"
        private const val STATE = "state"
    }

    @Mock
    private lateinit var req: HttpServletRequest
    @Mock
    private lateinit var authCodeService: AuthCodeService
    @Mock
    private lateinit var oAuthService: OAuth2Service
    @InjectMocks
    private lateinit var oAuthResource: OAuth2Resource

    private val authenticatedUser = AuthUserDto(
            JwtUtils.EMAIL,
            listOf(JwtUtils.ROLE_1, JwtUtils.ROLE_2),
            JwtUtils.FIRST_NAME,
            JwtUtils.LAST_NAME
    )

    @BeforeEach
    fun before() {
        oAuthResource.req = req
    }

    @Test
    fun test_login() {
        `when`(authCodeService.prepareAuthCodeLogin(req))
                .thenReturn(AUTH_CODE_LOGIN_URL)

        val response = oAuthResource.login()
        assertEquals(200, response.status)
        assertEquals(AuthCodeLoginDto(AUTH_CODE_LOGIN_URL), response.entity)
    }

    @Test
    fun test_code() {
        val authCodeResult = AuthCodeSuccessDto(COOKIE, POST_AUTH_REDIRECT)
        `when`(authCodeService.code(req, CODE, STATE))
                .thenReturn(authCodeResult)

        val response = oAuthResource.code(CODE, STATE)
        assertEquals(302, response.status)
        assertEquals(POST_AUTH_REDIRECT, response.headers.getFirst("Location"))
        assertEquals(COOKIE, response.headers.getFirst("Set-Cookie"))
    }

    @Test
    fun test_logout() {
        `when`(oAuthService.logout())
                .thenReturn(COOKIE)

        val response = oAuthResource.logout()
        assertEquals(200, response.status)
        assertEquals(COOKIE, response.headers.getFirst("Set-Cookie"))
    }

    @Test
    fun test_getAuthenticatedUser() {
        `when`(oAuthService.getAuthenticatedUser())
                .thenReturn(authenticatedUser)

        val response = oAuthResource.getAuthenticatedUser()
        assertEquals(200, response.status)
        assertEquals(authenticatedUser, response.entity)
    }

}