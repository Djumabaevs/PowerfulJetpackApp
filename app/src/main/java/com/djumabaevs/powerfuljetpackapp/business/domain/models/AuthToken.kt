package com.djumabaevs.powerfuljetpackapp.business.domain.models

data class AuthToken(
    val accountPk: Int,
    val token: String
)