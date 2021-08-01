package io.craigmiller160.jaxrs.oauth2.dto

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
data class ErrorDto(
        var message: String = "",
        var status: Int = 0
)