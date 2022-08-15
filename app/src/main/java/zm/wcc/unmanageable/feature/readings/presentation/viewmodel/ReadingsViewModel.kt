package zm.wcc.unmanageable.feature.readings.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zm.wcc.unmanageable.feature.readings.data.base.ReadingsDatabase
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData
import zm.wcc.unmanageable.feature.readings.data.repository.ReadingsRepository


class ReadingsViewModel(application: Application) : AndroidViewModel(application) {

    // var job : CompletableJob? = null

    val query = mutableStateOf("")
    val searching = mutableStateOf(false)

    val allReadings : LiveData<List<ReadingData>>
    val searchResults : MutableLiveData<List<ReadingData>>

    private val repository : ReadingsRepository


    init {
        val readingsDao = ReadingsDatabase.getDatabase(application, viewModelScope, application.resources)
            .dao()
        repository = ReadingsRepository(readingsDao)

        searchResults = repository.searchResults

        allReadings = repository.allReadings

    }

    fun getReadings() : LiveData<List<ReadingData>> {
        return repository.getReadings()
    }

    fun getReadingByID(id : Int) {
        viewModelScope.launch(Dispatchers.IO)  {
            repository.getReadingByID(id)
        }
    }


    fun searchReadings(search : String) {
        return repository.searchReadings(search)
    }

    fun onQueryChanged(query : String) {
        this.query.value = query
    }

    fun onSearchingChanged(searching : Boolean) {
        this.searching.value = searching
    }

}







