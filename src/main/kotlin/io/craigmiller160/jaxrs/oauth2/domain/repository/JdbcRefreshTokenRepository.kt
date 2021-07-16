package io.craigmiller160.jaxrs.oauth2.domain.repository

import io.craigmiller160.jaxrs.oauth2.domain.SqlConnectionProvider
import io.craigmiller160.jaxrs.oauth2.domain.entity.JdbcAppRefreshToken
import io.craigmiller160.oauth2.domain.entity.AppRefreshToken
import io.craigmiller160.oauth2.domain.repository.AppRefreshTokenRepository
import java.sql.ResultSet

class JdbcRefreshTokenRepository(
    private val sqlConnectionProvider: SqlConnectionProvider
) : AppRefreshTokenRepository {
    private val deleteById = """
        DELETE FROM app_refresh_tokens
        WHERE id = ?
    """.trimIndent()
    private val selectByTokenId = """
        SELECT *
        FROM app_refresh_tokens
        WHERE token_id = ?
    """.trimIndent()

    override fun deleteById(id: Long) {
        sqlConnectionProvider.provide().use { conn ->
            conn.prepareStatement(deleteById).use { stmt ->
                stmt.setLong(1, id)
                stmt.executeUpdate()
            }
        }
    }

    private fun resultSetToEntity(rs: ResultSet): AppRefreshToken =
            JdbcAppRefreshToken(
                    id = rs.getLong("id"),
                    tokenId = rs.getString("token_id"),
                    refreshToken = rs.getString("refresh_token")
            )

    override fun findByTokenId(tokenId: String): AppRefreshToken? {
        return sqlConnectionProvider.provide().use { conn ->
            conn.prepareStatement(selectByTokenId).use { stmt ->
                stmt.setString(1, tokenId)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        resultSetToEntity(rs)
                    } else {
                        null
                    }
                }
            }
        }
    }

    override fun removeByTokenId(tokenId: String) {
        TODO("Not yet implemented")
    }

    override fun save(token: AppRefreshToken): AppRefreshToken {
        TODO("Not yet implemented")
    }
}