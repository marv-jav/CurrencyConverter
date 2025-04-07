package com.assessement.currencyconverter.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessement.currencyconverter.R
import com.assessement.currencyconverter.presentation.ui.theme.borderColor
import com.assessement.currencyconverter.presentation.ui.theme.currencyTextColor
import com.assessement.currencyconverter.presentation.ui.theme.iconTint
import com.assessement.currencyconverter.presentation.ui.theme.poppins_semi_bold
import com.assessement.currencyconverter.utils.getFlagResourceId

@Composable
fun CurrencyDropdown(
    modifier: Modifier = Modifier,
    selectedCurrency: String = "EUR",
    onCurrencySelected: (String) -> Unit = {},
    currencyList: List<String> = listOf()
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(5.dp))
                .height(50.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val flagResourceId = getFlagResourceId(selectedCurrency)
            Image(
                painter = painterResource(id = flagResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, shape = RoundedCornerShape(100.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                selectedCurrency,
                style = poppins_semi_bold,
                fontSize = 18.sp,
                color = currencyTextColor
            )

            Spacer(modifier = Modifier.width(20.dp))

            Image(
                painter = painterResource(R.drawable.down_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(14.dp)
                    .clickable { expanded = true },
                colorFilter = ColorFilter.tint(color = iconTint)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            currencyList.forEach { currencyCode ->
                val flagResourceId = getFlagResourceId(currencyCode)
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = flagResourceId),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = currencyCode)
                        }
                    },
                    onClick = {
                        onCurrencySelected(currencyCode)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun CurrencyDropdownPrev() {
    var selectedCurrency by remember { mutableStateOf("EUR") }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CurrencyDropdown(
            selectedCurrency = selectedCurrency,
            onCurrencySelected = { selectedCurrency = it },
            currencyList = listOf("USD", "EUR", "JPY", "GBP", "INR"),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}