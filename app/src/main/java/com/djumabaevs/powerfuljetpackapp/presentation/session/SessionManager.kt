package com.djumabaevs.powerfuljetpackapp.presentation.session

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton
import com.djumabaevs.powerfuljetpackapp.presentation.session.SessionEvents.*
import com.djumabaevs.powerfuljetpackapp.presentation.util.DataStoreKeys
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
            appDataStoreManager.readValue(DataStoreKeys.PREVIOUS_AUTH_USER)?.let { email ->
                onTriggerEvent(SessionEvents.CheckPreviousAuthUser(email))
            }?: onFinishCheckingPrevAuthUser()
        }
    }

    fun onTriggerEvent(event: SessionEvents){
        when(event){
            is SessionEvents.Login -> {
                login(event.authToken)
            }
            is SessionEvents.Logout -> {
                logout()
            }
            is SessionEvents.CheckPreviousAuthUser -> {
                checkPreviousAuthUser(email = event.email)
            }
            is SessionEvents.OnRemoveHeadFromQueue ->{
                removeHeadFromQueue()
            }
        }
    }

    private fun removeHeadFromQueue(){
        state.value?.let { state ->
            try {
                val queue = state.queue
                queue.remove() // can throw exception if empty
                this.state.value = state.copy(queue = queue)
            }catch (e: Exception){
                Log.d(TAG, "removeHeadFromQueue: Nothing to remove from DialogQueue")
            }
        }
    }
}