package com.assessement.currencyconverter.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CurrencyScreen(modifier: Modifier = Modifier) {
    var selectedCurrency by remember { mutableStateOf("EUR") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(CIcons.Menu),
                null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    buttonColor
                )
            )
            Text("Sign up", fontSize = 20.sp, style = poppins_bold, color = buttonColor)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(100.dp)
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
            selectedCurrency = "EUR",
            onAmountChange = {},
            onCurrencyChange = {},
            onTextChange = {})
        Spacer(modifier = Modifier.height(17.dp))
        CurrencyInput(
            selectedCurrency = "EUR",
            onAmountChange = {},
            onCurrencyChange = {},
            onTextChange = {}
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyDropdown(
                selectedCurrency = selectedCurrency,
                onCurrencySelected = { selectedCurrency = it },
                currencyList = listOf("USD", "EUR", "JPY", "GBP", "INR"),
            )
            Image(
                painter = painterResource(CIcons.Swap), null, colorFilter = ColorFilter.tint(
                    iconTint
                )
            )
            CurrencyDropdown(
                selectedCurrency = selectedCurrency,
                onCurrencySelected = { selectedCurrency = it },
                currencyList = listOf("USD", "EUR", "JPY", "GBP", "INR"),
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(buttonColor, shape = RoundedCornerShape(6.dp))
        ) {
            Text(
                "Convert",
                modifier = Modifier.align(Alignment.Center),
                style = poppins_medium,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Preview()
@Composable
private fun CurrencyScreenPrev() {
    CurrencyScreen()
}