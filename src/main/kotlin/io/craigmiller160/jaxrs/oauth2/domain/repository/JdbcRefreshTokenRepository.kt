package io.craigmiller160.jaxrs.oauth2.domain.repository

import io.craigmiller160.oauth2.domain.entity.AppRefreshToken
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository

class JdbcRefreshTokenRepository : AppRefreshTokenRepository {

    // TODO need datasource from DI

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findByTokenId(tokenId: String): AppRefreshToken? {
        TODO("Not yet implemented")
    }

    override fun removeByTokenId(tokenId: String) {
        TODO("Not yet implemented")
    }

    override fun save(token: AppRefreshToken): AppRefreshToken {
        TODO("Not yet implemented")
    }
}