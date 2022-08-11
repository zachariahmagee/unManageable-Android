package zm.wcc.unmanageable.feature.readings.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableJob
import zm.wcc.unmanageable.feature.readings.data.base.ReadingsDatabase
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData
import zm.wcc.unmanageable.feature.readings.data.repository.ReadingsRepository

class ReadingsViewModel(application: Application) : AndroidViewModel(application) {

    // var job : CompletableJob? = null

    private val repository : ReadingsRepository



    init {
        val readingsDao = ReadingsDatabase.getDatabase(application, viewModelScope, application.resources)
            .dao()
        repository = ReadingsRepository(readingsDao)

        //val allReadings : LiveData<List<ReadingData>> = getReadings()

    }

    fun getReadings() : LiveData<List<ReadingData>> {
        return repository.getReadings()
    }

    fun searchReadings(search : String) : LiveData<List<ReadingData>> {
        return repository.searchReadings(search)
    }
}