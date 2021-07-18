package io.craigmiller160.jaxrs.oauth2.service

import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.provider.AuthUserProvider
import io.craigmiller160.oauth2.security.CookieCreator
import io.craigmiller160.oauth2.service.OAuth2Service
import io.craigmiller160.oauth2.service.impl.OAuth2ServiceImpl
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class OAuth2ServiceInjectImpl @Inject constructor(
        oAuth2Config: OAuth2Config,
        appRefreshTokenRepo: AppRefreshTokenRepository,
        cookieCreator: CookieCreator,
        authUserProvider: AuthUserProvider
) : OAuth2Service by OAuth2ServiceImpl(
        oAuth2Config,
        appRefreshTokenRepo,
        cookieCreator,
        authUserProvider
)