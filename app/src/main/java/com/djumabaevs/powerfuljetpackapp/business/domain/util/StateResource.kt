package com.djumabaevs.powerfuljetpackapp.business.domain.util


data class StateMessage(val response: Response)

data class Response(
    val message: String?,
    val uiComponentType: UIComponentType,
    val messageType: MessageType
)