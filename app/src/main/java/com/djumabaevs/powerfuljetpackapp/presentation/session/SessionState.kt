package com.djumabaevs.powerfuljetpackapp.presentation.session

import com.djumabaevs.powerfuljetpackapp.business.domain.models.AuthToken
import com.djumabaevs.powerfuljetpackapp.business.domain.util.Queue
import com.djumabaevs.powerfuljetpackapp.business.domain.util.StateMessage

data class SessionState(
    val isLoading: Boolean = false,
    val authToken: AuthToken? = null,
    val didCheckForPreviousAuthUser: Boolean = false,
    val queue: Queue<StateMessage> = Queue(mutableListOf()),
)