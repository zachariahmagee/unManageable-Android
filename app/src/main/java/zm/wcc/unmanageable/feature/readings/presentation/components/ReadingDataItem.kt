package zm.wcc.unmanageable.feature.readings.presentation.components


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData
import zm.wcc.unmanageable.feature.readings.presentation.ui.Screen
import zm.wcc.unmanageable.ui.theme.*

@Composable
fun ReadingDataItem(
    navController: NavController,
    readingDataItem: ReadingData,
    color : Color
) {
    val title : String = readingDataItem.title
    val reading : String = readingDataItem.reading
    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    // Compute the screen width using the actual display width and the density of the display.
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 16.dp
    val scroll = rememberScrollState()
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = color,
        modifier = Modifier
            .padding(16.dp)
            .clickable(
                onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(title, reading))
                }
            )
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = readingDataItem.title,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = milk_white,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            //Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = readingDataItem.reading,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
                    .verticalScroll(
                        state = scroll),
                style = TextStyle(
                    color = milk_white,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun ReadingDataItemDetail(
    navController: NavController,
    title : String?,
    reading : String?
) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    // Compute the screen width using the actual display width and the density of the display.
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 16.dp
    val scroll = rememberScrollState()
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = charcoal,
        modifier = Modifier.padding(16.dp),
        border = BorderStroke(
            width = 8.dp,
            color = matte_black,),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (title != null) {
                Text(
                    text = title,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        color = milk_white,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            //Spacer(modifier = Modifier.padding(4.dp))
            if (reading != null) {
                Text(
                    text = reading,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .verticalScroll(
                            state = scroll),
                    style = TextStyle(
                        color = milk_white,
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}













