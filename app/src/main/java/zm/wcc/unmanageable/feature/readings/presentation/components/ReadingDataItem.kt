package zm.wcc.unmanageable.feature.readings.presentation.components

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zm.wcc.unmanageable.feature.readings.data.entity.ReadingData
import zm.wcc.unmanageable.ui.theme.charcoal
import zm.wcc.unmanageable.ui.theme.milk_white

@Preview(showBackground = true)
@Composable
fun Preview() {
    val item2 = ReadingData(
        number = 2,
        title = "Out of God's Hands",
        reading ="When we look back, we realize that the things which came to us when we put ourselves in God's hands were better than anything we could have planned. \"My depression deepened unbearable, and finally it seemed to me as though I were at the very bottom of the pit. For the moment, the last vestige of my proud obstinacy was crushed. All at once I found myself crying out, \"If there is a God, let Him show Himself! I am ready to do anything, anything!\" Suddenly the room lit up with a great white light. It seemed to me, in the mind's eye, that I was on a mountain and that a wind not of air but of spirit was blowing. And then it burst upon me that I was a free man. Slowly the ecstasy subsided. I lay on the bed, but now for a time I was in another world,a new world of consciouness. All about me and through me there was a wonderful feeling of Presence, and I thought to myself, \"So this is the God of the preachers!\" 1. ALCOHOLICS ANONYMOUS, P. 100 2. A.A. COMES OF AGE, P. 63"
    )
    ReadingDataItem(readingDataItem = item2)
}

@Composable
fun ReadingDataItem(
    readingDataItem: ReadingData
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
        modifier = Modifier.padding(16.dp)
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
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = readingDataItem.reading,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
                    .verticalScroll(
                        state = scroll)
                    ,
                style = TextStyle(
                    color = milk_white,
                    fontSize = 16.sp
                )
            )


        }
    }
}

