package com.paixao.dev.mbtest.compose.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.paixao.dev.mbtest.ui.theme.MBTestTheme

@Composable
fun CardInfo(
    modifier: Modifier = Modifier,
    important: Boolean = false,
    cardColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation =  if (important) 10.dp else 0.dp
        )
    ) {
        Column(content = content)
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Dynamic Color",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    showBackground = true
)
@Composable
fun CardInfoPreview() {
    MBTestTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            CardInfo(important = true) {
                Spacer(modifier = Modifier.size(300.dp))
            }
            Spacer(modifier = Modifier.size(15.dp))
            CardInfo(important = false) {
                Spacer(modifier = Modifier.size(300.dp))
            }
        }
    }
}
