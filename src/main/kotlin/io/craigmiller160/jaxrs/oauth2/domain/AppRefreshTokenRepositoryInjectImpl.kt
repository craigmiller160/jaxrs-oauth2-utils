package io.craigmiller160.jaxrs.oauth2.domain

import io.craigmiller160.oauth2.domain.SqlConnectionProvider
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.domain.repository.impl.AppRefreshTokenRepositoryImpl
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class AppRefreshTokenRepositoryInjectImpl @Inject constructor(
        private val sqlConnectionProvider: SqlConnectionProvider
) : AppRefreshTokenRepository by AppRefreshTokenRepositoryImpl(
        sqlConnectionProvider
)