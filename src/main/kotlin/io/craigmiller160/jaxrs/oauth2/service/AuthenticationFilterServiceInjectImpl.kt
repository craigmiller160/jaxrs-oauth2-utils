package io.craigmiller160.jaxrs.oauth2.service

import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.security.AuthenticationFilterService
import io.craigmiller160.oauth2.security.CookieCreator
import io.craigmiller160.oauth2.security.impl.AuthenticationFilterServiceImpl
import io.craigmiller160.oauth2.service.RefreshTokenService
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class AuthenticationFilterServiceInjectImpl @Inject constructor(
        oAuth2Config: OAuth2Config,
        refreshTokenService: RefreshTokenService,
        cookieCreator: CookieCreator
) : AuthenticationFilterService by AuthenticationFilterServiceImpl(
        oAuth2Config,
        refreshTokenService,
        cookieCreator
)