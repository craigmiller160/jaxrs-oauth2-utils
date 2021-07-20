package io.craigmiller160.jaxrs.oauth2.service

import io.craigmiller160.oauth2.client.AuthServerClient
import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.security.CookieCreator
import io.craigmiller160.oauth2.service.AuthCodeService
import io.craigmiller160.oauth2.service.impl.AuthCodeServiceImpl
import javax.inject.Inject

class AuthCodeServiceInjectImpl @Inject constructor(
        oAuth2Config: OAuth2Config,
        authServerClient: AuthServerClient,
        appRefreshTokenRepo: AppRefreshTokenRepository,
        cookieCreator: CookieCreator
) : AuthCodeService by AuthCodeServiceImpl(
        oAuth2Config,
        authServerClient,
        appRefreshTokenRepo,
        cookieCreator
)