package zm.wcc.unmanageable

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import zm.wcc.unmanageable.feature.readings.presentation.ui.Navigator
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.MainViewModelFactory
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.ReadingsViewModel
import zm.wcc.unmanageable.ui.theme.UnManageableTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnManageableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: ReadingsViewModel = viewModel(
                            it,
                            "ReadingsViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application
                            )
                        )
                        Navigator(viewModel = viewModel)
                    }
                }
            }
        }
    }
}