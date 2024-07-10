package com.paixao.dev.mbtest.compose.component

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.paixao.dev.mbtest.ui.theme.MBTestTheme

@Composable
fun TitleValuation(
    value: String,
    shiny: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    textColor: Color = MaterialTheme.colorScheme.inverseSurface,
    shinyTextColor: Color = MaterialTheme.colorScheme.primary,
    shinyColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    TitleValuationCompose(
        value = value,
        textStyle = textStyle,
        textColor = textColor,
        shinyTextColor = shinyTextColor,
        shinyColor = shinyColor,
        shiny = shiny
    )
}

@Composable
private fun TitleValuationCompose(
    value: String,
    textStyle: TextStyle,
    textColor: Color,
    shinyTextColor: Color,
    shinyColor: Color,
    shiny: Boolean
) {
    Box {
        Text(
            text = value,
            color = if (shiny) shinyTextColor else textColor,
            style = textStyle,
            fontWeight = FontWeight.ExtraBold
        )

        if (shiny) {
            val shinyColors = listOf(
                shinyColor.copy(alpha = 0.6f),
                shinyColor.copy(alpha = 0.0f),
                shinyColor.copy(alpha = 0.6f),
                shinyColor.copy(alpha = 0.0f),
                shinyColor.copy(alpha = 0.6f),
            )

            val transition = rememberInfiniteTransition(label = "InfiniteTransition")
            val translateAnim = transition.animateFloat(
                initialValue = 0f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = FastOutLinearInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ), label = "FloatAnimation"
            )

            val brush = Brush.linearGradient(
                colors = shinyColors,
                start = Offset.Zero,
                end = Offset(x = translateAnim.value, y = translateAnim.value)
            )

            Text(
                text = value,
                color = textColor,
                fontWeight = FontWeight.ExtraBold,
                style = textStyle.copy(
                    brush = brush
                )
            )
        }
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
private fun TitleValuationPreview() {
    MBTestTheme {
        TitleValuation("R$ 10,00", shiny = true)
    }
}