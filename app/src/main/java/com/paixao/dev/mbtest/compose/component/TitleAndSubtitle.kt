package com.paixao.dev.mbtest.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

enum class TextSize {
    Small,
    Medium,
    Large
}

@Composable
fun TitleAndSubTitle(
    title: String,
    subtitle: String,
    titleSize: TextSize = TextSize.Large,
    subtitleSize: TextSize = TextSize.Medium,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    subtitleColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    TitleAndSubTitleCompose(
        title = title,
        subtitle = subtitle,
        titleColor = titleColor,
        subtitleColor = subtitleColor,
        titleStyle = when (titleSize) {
            TextSize.Small -> MaterialTheme.typography.titleSmall
            TextSize.Medium -> MaterialTheme.typography.titleMedium
            TextSize.Large -> MaterialTheme.typography.titleLarge
        },
        subtitleStyle = when (subtitleSize) {
            TextSize.Small -> MaterialTheme.typography.bodySmall
            TextSize.Medium -> MaterialTheme.typography.bodyMedium
            TextSize.Large -> MaterialTheme.typography.bodyLarge
        }
    )
}

@Composable
private fun TitleAndSubTitleCompose(
    title: String,
    subtitle: String,
    titleColor: Color,
    subtitleColor: Color,
    titleStyle: TextStyle,
    subtitleStyle: TextStyle
) {
    Column {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle
        )
        Text(
            text = subtitle,
            color = subtitleColor,
            style = subtitleStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleAndSubTitlePreview() {
    TitleAndSubTitle("Title", "substitle")
}