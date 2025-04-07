package com.assessement.currencyconverter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // --- UI State ---

    // Selected source currency (e.g., "EUR")
    private val _selectedCurrency1 = MutableStateFlow("EUR")
    val selectedCurrency1: StateFlow<String> = _selectedCurrency1

    // Selected target currency (e.g., "JPY")
    private val _selectedCurrency2 = MutableStateFlow("JPY")
    val selectedCurrency2: StateFlow<String> = _selectedCurrency2

    // List of current exchange rates for selectedCurrency1
    private val _rates = MutableStateFlow<List<CurrencyRate>>(emptyList())
    val rates: StateFlow<List<CurrencyRate>> = _rates

    // Result of the conversion (amount * selected rate)
    private val _conversionResult = MutableStateFlow<Double?>(null)
    val conversionResult: StateFlow<Double?> = _conversionResult

    // Historical rates over time (used for chart display)
    private val _historicalRates = MutableStateFlow<Result<List<HistoricalRate>>>(Result.success(emptyList()))
    val historicalRates: StateFlow<Result<List<HistoricalRate>>> = _historicalRates

    init {
        // Load exchange rates from local DB or fallback to API
        fetchRatesFromDb()
    }

    // --- Data Fetching ---

    // Try fetching rates from local Room DB
    private fun fetchRatesFromDb() {
        Log.d("CurrencyConverterViewModel", "Fetching rates from DB...")
        viewModelScope.launch {
            currencyConverterUseCase.getRatesFromDb(_selectedCurrency1.value).collect { ratesFromDb ->
                if (ratesFromDb.isEmpty()) {
                    Log.d("CurrencyConverterViewModel", "No DB rates found. Fetching from API.")
                    fetchRatesFromApi()
                } else {
                    Log.d("CurrencyConverterViewModel", "Loaded rates from DB: $ratesFromDb")
                    _rates.value = ratesFromDb
                }
            }
        }
    }

    // Fetch rates from Fixer.io API and update local state
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

    // --- Currency Conversion ---

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

    // --- Historical Data Fetching ---

    fun fetchHistoricalRates(base: String, target: String, startDate: String, endDate: String) {
        Log.d("CurrencyConverterDot", "Fetching historical rates: $base to $target, $startDate - $endDate")
        viewModelScope.launch {
            val result = currencyConverterUseCase.getHistoricalRates(base, target, startDate, endDate)
            result.onSuccess { historicalRatesResponse ->
                Log.d("CurrencyConverterDot", "Historical response: ${historicalRatesResponse.size} days")
                val mappedRates = historicalRatesResponse.flatMap { response ->
                    response.rates.map { (date, rateMap) ->
                        val rate = rateMap[target] ?: 0.0
                        Log.d("CurrencyConverterDot", "Mapped rate for $date: $rate")
                        HistoricalRate(date, rate)
                    }
                }
                _historicalRates.value = Result.success(mappedRates)
            }
            result.onFailure {
                _historicalRates.value = Result.failure(it)
                Log.e("CurrencyConverterDot", "Failed to fetch historical rates: ${it.message}")
            }
        }
    }

    // --- State Updates ---

    fun setSelectedCurrency1(currency: String) {
        _selectedCurrency1.value = currency
        fetchRatesFromDb() // Update rates when base currency changes
    }

    fun setSelectedCurrency2(currency: String) {
        _selectedCurrency2.value = currency
    }
}
