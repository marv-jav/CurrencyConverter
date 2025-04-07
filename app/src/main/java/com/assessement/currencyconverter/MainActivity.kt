package com.assessement.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.assessement.currencyconverter.presentation.ui.screens.CurrencyScreen
import com.assessement.currencyconverter.presentation.viewmodel.CurrencyConverterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Tells Hilt to inject dependencies into this activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable full-screen content on devices with notches/rounded corners
        enableEdgeToEdge()

        // Set the content of the screen using Jetpack Compose
        setContent {
            // Get the CurrencyConverterViewModel injected by Hilt
            val viewModel: CurrencyConverterViewModel = hiltViewModel()

            // Display the CurrencyScreen and pass the ViewModel to it
            CurrencyScreen(viewModel = viewModel)
        }
    }
}