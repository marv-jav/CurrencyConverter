package com.assessement.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.assessement.currencyconverter.presentation.ui.screens.CurrencyScreen
import com.assessement.currencyconverter.presentation.viewmodel.CurrencyConverterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CurrencyConverterViewModel = hiltViewModel()
            CurrencyScreen(viewModel = viewModel)
        }
    }
}