package com.assessement.currencyconverter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessement.currencyconverter.data.remote.dto.HistoricalRatesResponse
import com.assessement.currencyconverter.domain.model.CurrencyRate
import com.assessement.currencyconverter.domain.model.HistoricalRate
import com.assessement.currencyconverter.domain.usecase.CurrencyConverterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyConverterUseCase: CurrencyConverterUseCase
) : ViewModel() {

    // UI States
    private val _selectedCurrency1 = MutableStateFlow("EUR")
    val selectedCurrency1: StateFlow<String> = _selectedCurrency1

    private val _selectedCurrency2 = MutableStateFlow("JPY")
    val selectedCurrency2: StateFlow<String> = _selectedCurrency2

    private val _rates = MutableStateFlow<List<CurrencyRate>>(emptyList())
    val rates: StateFlow<List<CurrencyRate>> = _rates

    private val _conversionResult = MutableStateFlow<Double?>(null)
    val conversionResult: StateFlow<Double?> = _conversionResult

    private val _historicalRates = MutableStateFlow<Result<List<HistoricalRate>>>(Result.success(emptyList()))
    val historicalRates: StateFlow<Result<List<HistoricalRate>>> = _historicalRates

    init {
        fetchRatesFromDb()
    }

    // Fetch rates from API
    private fun fetchRatesFromApi() {
        Log.d("CurrencyConverterViewModel", "Fetching rates from API...")
        viewModelScope.launch {
            val result = currencyConverterUseCase.fetchRatesFromApi(_selectedCurrency1.value)
            result.onSuccess {
                Log.d("CurrencyConverterViewModel", "Rates fetched from API: $it")
                _rates.value = it
            }
            result.onFailure {
                Log.e("CurrencyConverterViewModel", "Error fetching rates from API: ${it.message}")
            }
        }
    }

    // Get rates from DB (for offline use)
    private fun fetchRatesFromDb() {
        Log.d("CurrencyConverterViewModel", "Fetching rates from API...")
        viewModelScope.launch {
            currencyConverterUseCase.getRatesFromDb(_selectedCurrency1.value)
                .collect { ratesFromDb ->
                    if (ratesFromDb.isEmpty()) {
                        Log.d(
                            "CurrencyConverterViewModel",
                            "Rates not found in DB, falling back to API."
                        )
                        fetchRatesFromApi()
                    } else {
                        Log.d("CurrencyConverterViewModel", "Rates found in DB: $ratesFromDb")
                        _rates.value = ratesFromDb
                    }
                }
        }
    }

    fun fetchHistoricalRates(base: String, target: String, startDate: String, endDate: String) {
        Log.d("CurrencyConverterDot", "Fetching historical rates from $base to $target from $startDate to $endDate")

        viewModelScope.launch {
            val result = currencyConverterUseCase.getHistoricalRates(base, target, startDate, endDate)
            result.onSuccess { historicalRatesResponse ->
                Log.d("CurrencyConverterDot", "Historical rates response received: ${historicalRatesResponse.size} entries")
                val mappedRates = historicalRatesResponse.flatMap { response ->
                    response.rates.map { (date, rateMap) ->
                        val rate = rateMap[target] ?: 0.0
                        Log.d("CurrencyConverterDot", "Mapped rate for date $date: $rate")
                        HistoricalRate(date, rate)
                    }
                }
                _historicalRates.value = Result.success(mappedRates)
                Log.d("CurrencyConverterDot", "Mapped rates: ${mappedRates.size} entries")
            }
            result.onFailure {
                _historicalRates.value = Result.failure(it)
                Log.e("CurrencyConverterDot", "Error fetching historical rates: ${it.message}")
            }
        }
    }

    // Set selected currencies
    fun setSelectedCurrency1(currency: String) {
        _selectedCurrency1.value = currency
        fetchRatesFromDb()
    }

    fun setSelectedCurrency2(currency: String) {
        _selectedCurrency2.value = currency
    }

    // Convert amount
    fun convert(amount: Double) {
        val rate = _rates.value.find { it.target == _selectedCurrency2.value }
        if (rate != null) {
            val result = amount * rate.rate
            _conversionResult.value = result
            Log.d("CurrencyConverterResult", "Conversion Result: $result")
        } else {
            _conversionResult.value = null
            Log.d("CurrencyConverterResult", "No conversion rate found for target currency")
        }
    }
}