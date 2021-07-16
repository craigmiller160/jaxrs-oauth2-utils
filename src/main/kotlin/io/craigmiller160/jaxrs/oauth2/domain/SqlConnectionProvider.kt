package io.craigmiller160.jaxrs.oauth2.domain

import java.sql.Connection

fun interface SqlConnectionProvider {
    fun provide(): Connection
}