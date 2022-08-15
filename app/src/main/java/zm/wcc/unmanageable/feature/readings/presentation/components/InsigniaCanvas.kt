package zm.wcc.unmanageable.feature.readings.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zm.wcc.unmanageable.ui.theme.black
import zm.wcc.unmanageable.ui.theme.matte_black

@Preview(showBackground = true)
@Composable
fun InsigniaCanvas() {

    Surface(modifier = Modifier
        //.fillMaxWidth()
        .height(100.dp).width(100.dp),
        color = matte_black
    ) {
        Box(//modifier = Modifier.width(50.dp),
        contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                drawCircle(color = black, radius = 48.dp.toPx(), style = Stroke(4.dp.toPx()))
                val trianglePath = Path().apply {
                    moveTo(50.dp.toPx(), 3.dp.toPx())
                    lineTo(88.dp.toPx(),75.dp.toPx())
                    lineTo(12.dp.toPx(), 75.dp.toPx())
                    lineTo(50.dp.toPx(), 3.dp.toPx())
                }
                drawPath(trianglePath, color = black, style = Stroke(4.dp.toPx()))

            })

        }

    }
}