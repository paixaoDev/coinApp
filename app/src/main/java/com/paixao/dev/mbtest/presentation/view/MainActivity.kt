package com.paixao.dev.mbtest.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.paixao.dev.mbtest.CoinApp
import com.paixao.dev.mbtest.presentation.viewmodel.CoinViewModel
import com.paixao.dev.mbtest.ui.theme.MBTestTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val mainViewModel : CoinViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MBTestTheme {
                CoinApp(mainViewModel)
            }
        }
    }
}
