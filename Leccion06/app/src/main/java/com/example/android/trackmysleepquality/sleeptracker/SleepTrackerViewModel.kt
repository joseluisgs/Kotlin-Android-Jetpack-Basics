/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var tonight = MutableLiveData<SleepNight?>()
    private val nights = database.getAllNights()
    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    val startButtonVisible = Transformations.map(tonight) {
        it == null
    }
    val stopButtonVisible = Transformations.map(tonight) {
        it != null
    }
    val clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    init {
        initializeTonight()
    }

    // Lanzamos una tarea en una corrutina
    // I. You can access the CoroutineScope of a ViewModel through the viewModelScope
    // El patrón usado
    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight) {
        //  // No need to specify the Dispatcher, Room uses Dispatchers.IO.
        database.insert(night)
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    private suspend fun update(night: SleepNight) {
        database.update(night)
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            tonight.value = null
        }
    }

    suspend fun clear() {
        database.clear()
        _showSnackbarEvent.value = true
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}

/**
 * Important: Now you should be noticing a pattern:

Launch a coroutine that runs on the main UI thread, because the result from that coroutine affects what is displayed in the UI. You can access the CoroutineScope of a ViewModel through the viewModelScope property of the ViewModel, as shown in the following example:
Call a suspend function to do the long-running work, so that you don't block the UI thread while waiting for the result.
The result of the long-running work may affect the UI, but its operation is independent from the UI. For efficiency, switch to the I/O dispatcher, (Room generates his code for you). The I/O dispatcher uses a thread pool that's optimized and set aside for these kinds of operations.
Then call the long running function to do the work.
The pattern is shown below.

Without Room


fun someWorkNeedsToBeDone {
viewModelScope.launch {
suspendFunction()
}
}

suspend fun suspendFunction() {
withContext(Dispatchers.IO) {
longrunningWork()
}
}
Using Room


// Using Room
fun someWorkNeedsToBeDone {
viewModelScope.launch {
suspendDAOFunction()
}
}

suspend fun suspendDAOFunction() {
// No need to specify the Dispatcher, Room uses Dispatchers.IO.
longrunningDatabaseWork()
}

 */
