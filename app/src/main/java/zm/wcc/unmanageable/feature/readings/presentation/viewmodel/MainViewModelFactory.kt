package zm.wcc.unmanageable.feature.readings.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val application : Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReadingsViewModel::class.java)) {
            ReadingsViewModel(application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}