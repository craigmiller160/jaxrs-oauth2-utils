package io.craigmiller160.jaxrs.oauth2.domain.repository

import org.h2.tools.Server
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JdbcRefreshTokenRepositoryTest {

    companion object {
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

    @BeforeEach
    fun setup() {
        println("URL: ${server.url}") // TODO delete this
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
        TODO("Finish this")
    }

    @Test
    fun `save() existing token`() {
        TODO("Finish this")
    }

}