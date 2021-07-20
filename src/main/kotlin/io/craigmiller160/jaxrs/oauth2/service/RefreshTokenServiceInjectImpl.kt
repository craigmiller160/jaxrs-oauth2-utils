package io.craigmiller160.jaxrs.oauth2.service

import io.craigmiller160.oauth2.client.AuthServerClient
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import io.craigmiller160.oauth2.service.RefreshTokenService
import io.craigmiller160.oauth2.service.impl.RefreshTokenServiceImpl
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class RefreshTokenServiceInjectImpl @Inject constructor(
        appRefreshTokenRepo: AppRefreshTokenRepository,
        authServerClient: AuthServerClient
) : RefreshTokenService by RefreshTokenServiceImpl(
        appRefreshTokenRepo,
        authServerClient
)