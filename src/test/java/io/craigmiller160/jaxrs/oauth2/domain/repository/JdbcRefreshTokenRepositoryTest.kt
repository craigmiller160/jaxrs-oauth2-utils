package io.craigmiller160.jaxrs.oauth2.domain.repository

import io.craigmiller160.jaxrs.oauth2.domain.SqlConnectionProvider
import io.craigmiller160.jaxrs.oauth2.domain.entity.JdbcAppRefreshToken
import org.h2.tools.Server
import org.junit.jupiter.api.*
import java.sql.DriverManager

class JdbcRefreshTokenRepositoryTest {

    companion object {
        private const val TOKEN_ID = "tokenId"
        private const val REFRESH_TOKEN = "refreshToken"

        lateinit var server: Server

        @BeforeAll
        @JvmStatic
        fun createServer() {
            server = Server.createTcpServer()
            server.start()
        }

        @AfterAll
        @JvmStatic
        fun destroyServer() {
            server.stop()
        }
    }

    private lateinit var repo: JdbcRefreshTokenRepository

    private fun getJdbcUrl(): String {
        val schema = javaClass.classLoader.getResource("schema.sql").toURI().path
        println(schema) // TODO delete this
        return "jdbc:h2:mem:${server.url}/test_db;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL;INIT=RUNSCRIPT FROM '$schema'"
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
        repo.save(token)
        // TODO finish this
    }

    @Test
    fun `save() existing token`() {
        TODO("Finish this")
    }

}