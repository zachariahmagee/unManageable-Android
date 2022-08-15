package zm.wcc.unmanageable.feature.readings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import zm.wcc.unmanageable.feature.readings.presentation.ui.getAllReadingCategories
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.ReadingsViewModel
import zm.wcc.unmanageable.ui.theme.getColors
import zm.wcc.unmanageable.ui.theme.matte_black
import zm.wcc.unmanageable.ui.theme.milk_white


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(navController: NavController, viewModel: ReadingsViewModel) {
    val allReadings = viewModel.getReadings().observeAsState(listOf())
    val searchResults = viewModel.searchResults.observeAsState(listOf())
    val query = viewModel.query.value
    val searching = viewModel.searching.value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = matte_black)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = matte_black,
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = query,
                        onValueChange = { searchTerm ->
                            viewModel.onQueryChanged(searchTerm)
                        },
                        textStyle = TextStyle(color = milk_white, fontSize = 20.sp),
                        label = { Text(text = "Search",
                                       color = milk_white,
                                       fontSize = 12.sp) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search for a reading"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    viewModel.onSearchingChanged(false)
                                    viewModel.onQueryChanged("")
                                })
                        },
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.onSearchingChanged(true)
                                viewModel.searchReadings(query)
                                keyboardController?.hide()
                            },
                        )
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(getAllReadingCategories().size) {
                        for (category in getAllReadingCategories()) {
                            Chip(
                                category = category.value,
                                onExecuteSearch = {
                                    viewModel.onSearchingChanged(true)
                                    viewModel.onQueryChanged(category.value.lowercase())
                                    viewModel.searchReadings(query)
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = matte_black)
        ) {
            val list = if (searching) searchResults else allReadings
            list.value.let { list ->
                items(list.size) { i ->
                    val colors = getColors()
                    val readingItem = list[i]
                    ReadingDataItem(navController, readingItem, colors[i % colors.size])
                }
            }
        }
    }
}
