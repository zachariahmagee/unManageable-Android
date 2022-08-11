package zm.wcc.unmanageable.feature.readings.data.repository

import androidx.lifecycle.LiveData
import zm.wcc.unmanageable.feature.readings.data.base.ReadingsDao
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData

class ReadingsRepository(private val readingsDao: ReadingsDao) {

    fun getReadings() : LiveData<List<ReadingData>> {
        return readingsDao.getReadings()
    }

    fun searchReadings(search : String) : LiveData<List<ReadingData>> {
        return readingsDao.searchReadings(search)
    }
}