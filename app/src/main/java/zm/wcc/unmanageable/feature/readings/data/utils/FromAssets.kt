package zm.wcc.unmanageable.feature.readings.data.utils

import android.content.Context
import org.json.JSONArray
import zm.wcc.unmanageable.R
import java.io.BufferedReader
import java.io.InputStream

fun readJSONFromAsset(context: Context) : String {
    val json : String
    try {
        val inputStream : InputStream = context.assets.open(
            context.getString(
                R.string.readingsAsset
            )
        )
        json = inputStream.bufferedReader().use {
            it.readText()
        }
    } catch (e: Exception) {
        e.localizedMessage
        return "Unable to read JSON from assets"
    }
    return json
}

fun loadJSONArray(context: Context):JSONArray {

    val inputStream = context.assets.open(
        context.getString(
            R.string.readingsAsset
        )
    )
    BufferedReader(inputStream.reader()).use {
        return JSONArray(it.readText())
    }
}