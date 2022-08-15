package zm.wcc.unmanageable.feature.readings.presentation.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zm.wcc.unmanageable.ui.theme.black
import zm.wcc.unmanageable.ui.theme.charcoal


@Composable
fun Chip(
    category : String,
    onExecuteSearch : (String) -> Unit = {}
) {
    Surface(
        modifier = Modifier.padding(6.dp),
        border = BorderStroke(1.dp, black),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = charcoal
    ){
        Row(
            modifier = Modifier
                .clickable( onClick = {onExecuteSearch(category)})
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(8.dp))
        }
    }
}