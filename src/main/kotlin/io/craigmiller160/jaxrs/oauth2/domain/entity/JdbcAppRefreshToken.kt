package io.craigmiller160.jaxrs.oauth2.domain.entity

import io.craigmiller160.oauth2.domain.entity.AppRefreshToken

data class JdbcAppRefreshToken(
        override val id: Long,
        override val tokenId: String,
        override val refreshToken: String
) : AppRefreshToken
