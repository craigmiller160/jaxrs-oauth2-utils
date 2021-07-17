package io.craigmiller160.jaxrs.oauth2.security

import io.craigmiller160.oauth2.security.AuthenticatedUser

data class AuthenticatedUserImpl(
        override val userName: String,
        override val roles: List<String>,
        override val firstName: String,
        override val lastName: String,
        override val tokenId: String
) : AuthenticatedUser