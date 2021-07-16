package io.craigmiller160.jaxrs.oauth2.domain.repository

import io.craigmiller160.jaxrs.oauth2.domain.SqlConnectionProvider
import io.craigmiller160.jaxrs.oauth2.domain.entity.JdbcAppRefreshToken
import org.h2.tools.Server
import org.junit.jupiter.api.*
import java.nio.file.Files
import java.sql.Connection
import java.sql.DriverManager
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JdbcRefreshTokenRepositoryTest {

    companion object {
        private const val TOKEN_ID = "tokenId"
        private const val REFRESH_TOKEN = "refreshToken"

        lateinit var server: Server

        @BeforeAll
        @JvmStatic
        fun createServer() {
            server = Server.createTcpServer("-ifNotExists")
            server.start()
        }

        @AfterAll
        @JvmStatic
        fun destroyServer() {
            server.stop()
        }
    }

    private val tempFile = Files.createTempFile("prefix", "suffix")
    private lateinit var repo: JdbcRefreshTokenRepository

    private fun getJdbcUrl(): String {
        val schema = javaClass.classLoader.getResource("schema.sql").toURI().path
        return "jdbc:h2:${server.url}/${tempFile.toFile().absolutePath};MODE=PostgreSQL;INIT=RUNSCRIPT FROM '$schema'"
    }

    @BeforeEach
    fun setup() {
        val connProvider = SqlConnectionProvider {
            DriverManager.getConnection(getJdbcUrl())
        }
        repo = JdbcRefreshTokenRepository(connProvider)
    }

    @AfterEach
    fun clean() {
        DriverManager.getConnection(getJdbcUrl()).use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeUpdate("DELETE FROM app_refresh_tokens")
            }
        }
    }

    @Test
    fun `deleteById()`() {
        TODO("Finish this")
    }

    @Test
    fun `removeByTokenId()`() {
        TODO("Finish this")
    }

    @Test
    fun `findByTokenId()`() {
        TODO("Finish this")
    }

    @Test
    fun `save() new token`() {
        val token = JdbcAppRefreshToken(
                id = 0,
                tokenId = TOKEN_ID,
                refreshToken = REFRESH_TOKEN
        )
        val result = repo.save(token)
        assertEquals(token.copy(id = result.id), result)

        DriverManager.getConnection(getJdbcUrl()).use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT COUNT(*) FROM app_refresh_tokens").use { rs ->
                    assertTrue { rs.next() }
                    assertEquals(1, rs.getLong(1))
                }

                stmt.executeQuery("SELECT * FROM app_refresh_tokens").use { rs ->
                    assertTrue { rs.next() }
                    assertEquals(result.id, rs.getLong("id"))
                    assertEquals(result.tokenId, rs.getString("token_id"))
                    assertEquals(result.refreshToken, rs.getString("refresh_token"))
                }
            }
        }
    }

    @Test
    fun `save() existing token`() {
        TODO("Finish this")
    }

}