package com.djumabaevs.powerfuljetpackapp.presentation.session

import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton
import com.djumabaevs.powerfuljetpackapp.presentation.session.SessionEvents.*

/**
 * Think of this class as an "application viewmodel"
 * It keeps the authentication state of the user.
 */

@Singleton
class SessionManager
@Inject constructor(
    private val checkPreviousAuthUser: CheckPreviousAuthUser,
    private val logout: Logout,
    private val appDataStoreManager: AppDataStore,
) {
}