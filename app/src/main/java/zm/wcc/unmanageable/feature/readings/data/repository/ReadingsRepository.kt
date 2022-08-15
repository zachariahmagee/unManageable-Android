package zm.wcc.unmanageable.feature.readings.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import zm.wcc.unmanageable.feature.readings.data.base.ReadingsDao
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData

class ReadingsRepository(private val readingsDao: ReadingsDao) {

    private val scope = CoroutineScope(Dispatchers.Main)

    val searchResults = MutableLiveData<List<ReadingData>>()

    val allReadings : LiveData<List<ReadingData>> = readingsDao.getReadings()

    fun getReadings() : LiveData<List<ReadingData>> {
        return readingsDao.getReadings()
    }

    fun getReadingByID(id : Int) : ReadingData {
        return readingsDao.getReadingByID(id)
    }


    fun searchReadings(search : String) {
        scope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(search).await()
        }
    }

    private fun asyncFind(search: String) : Deferred<List<ReadingData>?> =
        scope.async(Dispatchers.IO) {
            return@async readingsDao.searchReadings(search)
        }

}