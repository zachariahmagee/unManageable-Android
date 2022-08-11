package zm.wcc.unmanageable.feature.readings.data.base

import android.content.Context
import android.content.res.Resources
import android.os.Debug
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import zm.wcc.unmanageable.R
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData


@Database(entities = [ReadingData::class], version = 1, exportSchema = false)
abstract class ReadingsDatabase : RoomDatabase() {

    abstract fun dao() : ReadingsDao

    private class ReadingsDatabaseCallback(
        private val scope : CoroutineScope,
        private val resources : Resources
    ) : RoomDatabase.Callback() {
        companion object {
            val TAG : String = "ReadingDatabaseCallback"
        }
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val readingsDao = database.dao()
                    prePopulateDatabase(readingsDao)
                }
            }
        }

        private suspend fun prePopulateDatabase(readingsDao : ReadingsDao) {
            val jsonString = resources.openRawResource(R.raw.readings)
                .bufferedReader().use { it.readText() }

            val typeToken = object : TypeToken<List<ReadingData>>() {}.type
            val  readingsInput = Gson().fromJson<List<ReadingData>>(jsonString, typeToken)
            val job = Job()
            scope.launch(Dispatchers.IO + job) {
                readingsDao.insertReadings(readingsInput)
                Log.i(TAG,"Successfully pre-populated room database")
                job.complete()
            }
        }
    }



    companion object {

        @Volatile
        private var INSTANCE : ReadingsDatabase? = null

        fun getDatabase(
            context : Context,
            scope : CoroutineScope,
            resources: Resources) : ReadingsDatabase {

            val temp = INSTANCE
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    ReadingsDatabase::class.java
                )
                    .addCallback(ReadingsDatabaseCallback(scope, resources))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}