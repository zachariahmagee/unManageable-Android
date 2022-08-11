package zm.wcc.unmanageable.feature.readings.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "readings_table",
indices = [Index(value = ["number", "title", "reading"])])
data class ReadingData(
    @PrimaryKey
    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number : Int = 0,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String = "",
    @ColumnInfo(name = "reading")
    @SerializedName("reading")
    val reading : String = ""
)
