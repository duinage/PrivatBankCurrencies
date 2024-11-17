package com.example.privatbankcurrencies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.privatbankcurrencies.item.CurrencyItem
import com.example.privatbankcurrencies.retrofit.RetrofitPrivate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel : ViewModel() {

    private val retrofitPrivate = RetrofitPrivate()

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate

    private val _currencyData = MutableLiveData<CurrencyItem?>()
    val currencyData: LiveData<CurrencyItem?>
        get() = _currencyData

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    init {
        // Set the current date by default
        val currentDate = dateFormat.format(Calendar.getInstance().time)
        _selectedDate.value = currentDate
        fetchCurrencyData(currentDate)
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, day)

        val formattedDate = dateFormat.format(selectedDate.time)
        _selectedDate.value = formattedDate
        fetchCurrencyData(formattedDate)
    }

    private fun fetchCurrencyData(date: String) {
        retrofitPrivate.getCurrencyExchange(date) { currencyItem ->
            if (currencyItem != null) {
                Log.d(TAG, "API Response: $currencyItem")
                _currencyData.value = currencyItem
            } else {
                Log.d(TAG, "API Call failed or returned null.")
                _currencyData.value = null
            }
        }
    }

    companion object {
        const val TAG = "XXX"
    }
}
