package com.assessement.currencyconverter.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
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
import com.assessement.currencyconverter.presentation.ui.theme.poppins_semi_bold

/**
 * Currency input field used to enter or display a currency value.
 *
 * @param modifier - Modifier for layout customization.
 * @param selectedCurrency - Currency code shown at the end of the field (e.g., "EUR").
 * @param onTextChange - Callback when the text input changes.
 * @param value - Current value to display in the input.
 * @param isInputEnabled - Whether the input field is editable.
 */
@Composable
fun CurrencyInput(
    modifier: Modifier = Modifier,
    selectedCurrency: String = "EUR",
    onTextChange: (String) -> Unit = {},
    value: String = "0.00",
    isInputEnabled: Boolean = true
) {
    // Local state to manage input field text
    var text by remember { mutableStateOf(value) }

    // Sync local state with external value changes
    LaunchedEffect(value) {
        text = value
    }

    Row(
        modifier = modifier
            .background(inputContainerColor, RoundedCornerShape(6.dp))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Editable or read-only currency input field
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                onTextChange(newText)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = inputContainerColor,
                unfocusedContainerColor = inputContainerColor,
                unfocusedIndicatorColor = inputContainerColor,
                focusedIndicatorColor = inputContainerColor,
                focusedTextColor = inputTextColor,
                unfocusedTextColor = inputTextColor,
                disabledContainerColor = inputContainerColor,
                disabledIndicatorColor = inputContainerColor
            ),
            modifier = Modifier.weight(2f),
            placeholder = {
                Text(
                    "Enter amount",
                    style = poppins_semi_bold,
                    fontSize = 18.sp,
                    color = currencyTextColor
                )
            },
            textStyle = poppins_bold,
            enabled = isInputEnabled
        )

        // Display currency code (e.g., USD, EUR)
        Text(
            selectedCurrency,
            modifier = Modifier.weight(1f),
            color = currencyTextColor,
            textAlign = TextAlign.End,
            style = poppins_semi_bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.width(20.dp))
    }
}

/**
 * Preview for CurrencyInput composable.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CInputPrev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        CurrencyInput(
            selectedCurrency = "USD",
            value = "123.45",
            isInputEnabled = true
        )
    }
}
