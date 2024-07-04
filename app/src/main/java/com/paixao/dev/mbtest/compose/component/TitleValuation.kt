package com.paixao.dev.mbtest.compose.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitleValuation(
    value: String,
    shiny: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    textColor: Color = MaterialTheme.colorScheme.inverseSurface
) {
    TitleValuationCompose(
        value = value,
        textStyle = textStyle,
        textColor = textColor,
        shiny = shiny
    )
}

@Composable
private fun TitleValuationCompose(
    value: String,
    textStyle: TextStyle,
    textColor: Color,
    shiny: Boolean
) {
    Box {
        Text(
            text = value,
            color = textColor,
            style = textStyle,
            fontWeight = FontWeight.ExtraBold
        )

        if (shiny) {
            val shinyColors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.1f),
                Color.LightGray.copy(alpha = 0.6f),
            )

            val transition = rememberInfiniteTransition(label = "InfiniteTransition")
            val translateAnim = transition.animateFloat(
                initialValue = 0f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
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

@Preview(showBackground = true)
@Composable
private fun TitleValuationPreview() {
    TitleValuation("R$ 10,00", shiny = true)
}