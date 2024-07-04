package com.paixao.dev.mbtest.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.paixao.dev.mbtest.CoinApp
import com.paixao.dev.mbtest.ui.theme.MBTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MBTestTheme {
                CoinApp()
            }
        }
    }
}
