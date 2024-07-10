package com.paixao.dev.mbtest.compose.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paixao.dev.mbtest.R
import com.paixao.dev.mbtest.ui.theme.MBTestTheme


@Composable
fun ExchangeListItem(
    id: String,
    name: String,
    image: String? = "",
    value: String,
    fav: Boolean = false,
    elevate: Boolean = false,
    click: () -> Unit = {}
) {
    CardInfo(
        modifier = Modifier.clickable {
            click.invoke()
        },
        important = elevate
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                if (!image.isNullOrEmpty()) {
                    AsyncImage(
                        modifier = Modifier.size(40.dp),
                        model = image,
                        contentDescription = "$name image"
                    )
                } else {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "$name icon"
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                Box(modifier = Modifier.weight(1f)) {
                    TitleAndSubTitle(
                        title = name,
                        subtitle = "ID: $id",
                        titleSize = TextSize.Medium,
                        subtitleSize = TextSize.Small
                    )
                }

                TitleValuation(
                    value = value,
                    shiny = fav
                )
            }
        }
    }
}


@Preview(
    name = "Light Mode"
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Dynamic Color",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE
)
@Composable
private fun ExchangeItemPreview() {
    MBTestTheme {
        ExchangeListItem(
            id = "NUB_00",
            name = "Nubank",
            image = "",
            value = "R$ 10,00",
            fav = true
        )
    }
}