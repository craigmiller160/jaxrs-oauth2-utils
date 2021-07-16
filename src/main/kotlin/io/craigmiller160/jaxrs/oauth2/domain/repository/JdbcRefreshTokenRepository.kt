package io.craigmiller160.jaxrs.oauth2.domain.repository

import io.craigmiller160.jaxrs.oauth2.domain.SqlConnectionProvider
import io.craigmiller160.oauth2.domain.entity.AppRefreshToken
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository

class JdbcRefreshTokenRepository(
    private val sqlConnectionProvider: SqlConnectionProvider
) : AppRefreshTokenRepository {
    private val deleteById = """
        DELETE FROM app_refresh_tokens
        WHERE id = ?
    """.trimIndent()

    override fun deleteById(id: Long) {
        sqlConnectionProvider.provide().use { conn ->
            conn.prepareStatement(deleteById).use { stmt ->
                stmt.setLong(1, id)
                stmt.executeUpdate()
            }
        }
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