package com.assessement.currencyconverter.presentation.ui.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessement.currencyconverter.domain.model.HistoricalRate
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@Composable
fun CurrencyGraph(rates: List<HistoricalRate>) {
    val lineData = remember(rates) {
        listOf(
            Line(
                label = "Exchange Rate",
                values = rates.map { it.rate },
                color = SolidColor(Color(0xFF23af92)),
                firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(width = 2.dp),
            )
        )
    }

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp),
        data = lineData,
        animationMode = AnimationMode.Together { it * 500L },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun CurrencyGraphPrev() {

}
