package com.paixao.dev.mbtest.compose.component

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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paixao.dev.mbtest.R


@Composable
fun ExchangeItem(
    id: String,
    name: String,
    image: Painter? = null,
    value: String,
    time: String,
    fav: Boolean,
    click: () -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable {
            click.invoke()
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 5.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                image?.let {
                    Image(
                        modifier = Modifier.size(70.dp),
                        painter = image,
                        contentDescription = "$name icon"
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                Box(modifier = Modifier.weight(1f)) {
                    TitleAndSubTitle(title = name, subtitle = id)
                }

                TitleValuation(
                    value = value,
                    shiny = fav
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Text(
                text = time,
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun ExchangeItemPreview() {
    ExchangeItem(
        id = "NUB_00",
        name = "Nubank",
        image = painterResource(id = R.drawable.ic_launcher_background),
        value = "R$ 10,00",
        time = "Em 24 Horaas",
        fav = true
    )
}