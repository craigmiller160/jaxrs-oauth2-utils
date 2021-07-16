package io.craigmiller160.jaxrs.oauth2.client

import io.craigmiller160.oauth2.client.AuthServerClient
import io.craigmiller160.oauth2.client.AuthServerClientImpl
import io.craigmiller160.oauth2.config.OAuth2Config
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class AuthServerClientInjectImpl @Inject constructor(
        oAuth2Config: OAuth2Config
) : AuthServerClient by AuthServerClientImpl(oAuth2Config)