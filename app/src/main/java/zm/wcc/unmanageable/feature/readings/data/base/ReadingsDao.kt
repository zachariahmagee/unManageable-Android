package zm.wcc.unmanageable.feature.readings.data.base

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData


@Dao
interface ReadingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadings(readings : List<ReadingData>)

    @Query("SELECT * FROM readings_table WHERE number = :id")
    fun getReadingByID(id : Int) : ReadingData

    @Query("SELECT * FROM readings_table")
    fun getReadings() : LiveData<List<ReadingData>>

    @Query("DELETE FROM readings_table")
    fun deleteAll()

    @Query("SELECT * FROM readings_table WHERE reading LIKE '%' || :search || '%'")
    fun searchReadings(search : String) : List<ReadingData>

}