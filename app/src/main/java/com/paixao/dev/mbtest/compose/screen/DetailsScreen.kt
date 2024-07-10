package com.paixao.dev.mbtest.compose.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.paixao.dev.mbtest.compose.component.CardInfo
import com.paixao.dev.mbtest.compose.component.TextSize
import com.paixao.dev.mbtest.compose.component.TitleAndSubTitle
import com.paixao.dev.mbtest.presentation.model.ExchangeDetails
import com.paixao.dev.mbtest.presentation.state.DetailScreenUiState
import com.paixao.dev.mbtest.presentation.viewmodel.ExchangeDetailViewModel
import com.paixao.dev.mbtest.ui.theme.MBTestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: ExchangeDetailViewModel = koinViewModel(),
    exchangeID: String = "USD"
) {
    viewModel.getExchange(exchangeID)

    val state by viewModel.state.collectAsState(initial = DetailScreenUiState.Loading())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (val ui = state) {
            is DetailScreenUiState.ExchangeDetail -> DetailsScreenComposable(ui.exchangeDetails)
            is DetailScreenUiState.Error -> {}
            is DetailScreenUiState.Failure -> {}
            is DetailScreenUiState.Loading -> {}
        }
    }
}

@Composable
fun DetailsScreenComposable(detail: ExchangeDetails) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        ExchangeSimpleDetail(detail)
    }
}

@Composable
fun ExchangeSimpleDetail(details: ExchangeDetails) {
    CardInfo {
        Column(
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 26.dp)
        ) {
            TitleAndSubTitle(
                title = "Exchange Name:",
                subtitle = details.name,
                titleSize = TextSize.Small
            )

            Spacer(modifier = Modifier.size(10.dp))

            TitleAndSubTitle(
                title = "Exchange ID:",
                subtitle = details.id,
                titleSize = TextSize.Small
            )

            Spacer(modifier = Modifier.size(10.dp))

            TitleAndSubTitle(
                title = "Volume ultima hora",
                subtitle = details.volumeHrs,
                titleSize = TextSize.Small
            )

            Spacer(modifier = Modifier.size(10.dp))

            TitleAndSubTitle(
                title = "Volume ultimo dia",
                subtitle = details.volumeDay,
                titleSize = TextSize.Small
            )

            Spacer(modifier = Modifier.size(10.dp))

            TitleAndSubTitle(
                title = "Volume ultimo mês",
                subtitle = details.volumeMonth,
                titleSize = TextSize.Small
            )
        }
    }
}

@Preview(
    name = "Light Mode",
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Dynamic Color",
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
)
@Composable
fun ExchangeSimpleDetailPreview() {
    MBTestTheme {
        ExchangeSimpleDetail(
            ExchangeDetails(
                "", "", "", "", "", ""
            )
        )
    }
}