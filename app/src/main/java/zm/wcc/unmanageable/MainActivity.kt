package zm.wcc.unmanageable

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingsList
import zm.wcc.unmanageable.feature.readings.presentation.components.ReadingDataItem
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.MainViewModelFactory
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.ReadingsViewModel
import zm.wcc.unmanageable.ui.theme.UnManageableTheme
import zm.wcc.unmanageable.ui.theme.matte_black

class MainActivity : ComponentActivity() {

    //private lateinit var readingsViewModel : ReadingsViewModel

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


                        ScreenSetup(viewModel)

                    }
                }
            }

            // readingsViewModel = ViewModelProvider(this)[ReadingsViewModel::class.java]
            //readingsViewModel.getReadings().observe(this, Observer<List<ReadingData>> {})

        }
    }
}

@Composable
fun ScreenSetup(viewModel: ReadingsViewModel) {
    val allReadings = viewModel.getReadings().observeAsState(listOf())


    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = matte_black)
    ) {
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("Search for a reading..") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = matte_black)
            //.verticalScroll(scrollState)
        ) {
            allReadings.value.let { allReadings ->
                items(allReadings.size) { i ->
                    val readingItem = allReadings[i]
                    ReadingDataItem(readingItem)
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnManageableTheme {


    }
}