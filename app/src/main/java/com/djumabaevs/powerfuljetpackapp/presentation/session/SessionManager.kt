package com.djumabaevs.powerfuljetpackapp.presentation.session

import androidx.lifecycle.MutableLiveData
import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton
import com.djumabaevs.powerfuljetpackapp.presentation.session.SessionEvents.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    private val TAG: String = "AppDebug"

    private val sessionScope = CoroutineScope(Dispatchers.Main)

    val state: MutableLiveData<SessionState> = MutableLiveData(SessionState())

    init {
        //Check if a user was authenticated in a previous session

        sessionScope.launch {
            appDataStoreManager.readValue(DataStoreKe)
        }
    }
}