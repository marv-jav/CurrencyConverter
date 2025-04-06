package com.assessement.currencyconverter.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessement.currencyconverter.presentation.ui.theme.currencyTextColor
import com.assessement.currencyconverter.presentation.ui.theme.inputContainerColor
import com.assessement.currencyconverter.presentation.ui.theme.inputTextColor
import com.assessement.currencyconverter.presentation.ui.theme.poppins_bold

@Composable
fun CurrencyInput(
    modifier: Modifier = Modifier,
    selectedCurrency: String = "EUR",
    onAmountChange: (String) -> Unit = {},
    onCurrencyChange: (String) -> Unit = {},
    onTextChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .background(inputContainerColor, RoundedCornerShape(6.dp))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                onTextChange(newText)
            },
            colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = inputContainerColor,
                    unfocusedContainerColor = inputContainerColor,
                    unfocusedIndicatorColor = inputContainerColor,
                    focusedIndicatorColor = inputContainerColor,
                    focusedTextColor = inputTextColor,
                    unfocusedTextColor = inputTextColor
                ),
            modifier = Modifier.weight(2f),
            placeholder = {
                Text(
                    "Enter amount",
                    style = poppins_bold,
                    fontSize = 18.sp,
                    color = currencyTextColor
                )
            },
            textStyle = poppins_bold,
        )
        Text(
            "EUR",
            modifier = Modifier.weight(1f),
            color = currencyTextColor,
            textAlign = TextAlign.End,
            style = poppins_bold,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.width(20.dp))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CInputPrev() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        CurrencyInput(modifier = Modifier.padding(horizontal = 12.dp)) {}
    }
}