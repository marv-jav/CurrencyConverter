package com.assessement.currencyconverter.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.assessement.currencyconverter.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val poppins_bold = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
    fontSize = 18.sp
)
val poppins_semi_bold = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
)
val poppins_medium = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
)
val poppins_regular = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
)
val poppins_extra_bold = TextStyle(
    fontFamily = FontFamily(Font(R.font.poppins_extra_bold)),
)