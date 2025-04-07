package com.assessement.currencyconverter.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessement.currencyconverter.presentation.ui.components.CIcons
import com.assessement.currencyconverter.presentation.ui.components.CurrencyDropdown
import com.assessement.currencyconverter.presentation.ui.components.CurrencyInput
import com.assessement.currencyconverter.presentation.ui.theme.buttonColor
import com.assessement.currencyconverter.presentation.ui.theme.iconTint
import com.assessement.currencyconverter.presentation.ui.theme.mainTextColor
import com.assessement.currencyconverter.presentation.ui.theme.poppins_bold
import com.assessement.currencyconverter.presentation.ui.theme.poppins_medium
import com.assessement.currencyconverter.presentation.viewmodel.CurrencyConverterViewModel

@Composable
fun CurrencyScreen(modifier: Modifier = Modifier, viewModel: CurrencyConverterViewModel) {
    val selectedCurrency1 by viewModel.selectedCurrency1.collectAsState()
    val selectedCurrency2 by viewModel.selectedCurrency2.collectAsState()
    val conversionResult by viewModel.conversionResult.collectAsState()
    val historicalRatesResult by viewModel.historicalRates.collectAsState()

    var amountInput by remember { mutableStateOf("") }

    val startDate = "2023-01-01"
    val endDate = "2023-12-31"

    fun fetchHistoricalData() {
        viewModel.fetchHistoricalRates(selectedCurrency1, selectedCurrency2, startDate, endDate)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(CIcons.Menu),
                null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(buttonColor)
            )
            Text("Sign up", fontSize = 20.sp, style = poppins_bold, color = buttonColor)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Currency\nCalculator",
                fontSize = 40.sp,
                style = poppins_bold,
                color = mainTextColor,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(buttonColor)
                    .padding(bottom = 20.dp)
            )
        }
        Spacer(modifier = Modifier.height(60.dp))

        CurrencyInput(
            selectedCurrency = selectedCurrency1,
            onTextChange = { newAmount -> amountInput = newAmount },
            value = amountInput,
            isInputEnabled = true
        )
        Spacer(modifier = Modifier.height(17.dp))
        CurrencyInput(
            selectedCurrency = selectedCurrency2,
            onTextChange = {},
            value = conversionResult?.toString() ?: "",
            isInputEnabled = false
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyDropdown(
                selectedCurrency = selectedCurrency1,
                onCurrencySelected = { viewModel.setSelectedCurrency1(it) },
                currencyList = listOf("USD", "EUR", "GBP", "JPY", "INR")
            )
            Image(
                painter = painterResource(CIcons.Swap),
                null,
                colorFilter = ColorFilter.tint(iconTint)
            )
            CurrencyDropdown(
                selectedCurrency = selectedCurrency2,
                onCurrencySelected = { viewModel.setSelectedCurrency2(it) },
                currencyList = listOf("USD", "EUR", "GBP", "JPY", "INR")
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(buttonColor, shape = RoundedCornerShape(6.dp))
                .clickable {
                    val amount = amountInput.toDoubleOrNull()
                    Log.d("Button", "Conversion Result: $amount")
                    if (amount != null) {
                        viewModel.convert(amount)
                        fetchHistoricalData()
                    }
                }
        ) {
            Text(
                "Convert",
                modifier = Modifier.align(Alignment.Center),
                style = poppins_medium,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}


@Preview()
@Composable
private fun CurrencyScreenPrev() {

}