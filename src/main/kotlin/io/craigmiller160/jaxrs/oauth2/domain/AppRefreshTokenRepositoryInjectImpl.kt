package io.craigmiller160.jaxrs.oauth2.domain

import io.craigmiller160.oauth2.config.OAuth2Config
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.domain.repository.impl.AppRefreshTokenRepositoryImpl
import io.craigmiller160.oauth2.provider.SqlConnectionProvider
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class AppRefreshTokenRepositoryInjectImpl @Inject constructor(
        private val oAuth2Config: OAuth2Config,
        private val sqlConnectionProvider: SqlConnectionProvider
) : AppRefreshTokenRepository by AppRefreshTokenRepositoryImpl(
        oAuth2Config,
        sqlConnectionProvider
)