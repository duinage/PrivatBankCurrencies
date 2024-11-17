package com.example.privatbankcurrencies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.privatbankcurrencies.item.CurrencyItem
import com.example.privatbankcurrencies.retrofit.RetrofitPrivate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var currentJob: Job? = null  // Змінна для зберігання корутини

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    init {
        val currentDate = dateFormat.format(Calendar.getInstance().time)
        _selectedDate.value = currentDate
        fetchCurrencyData(currentDate)
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, day)

        val formattedDate = dateFormat.format(selectedDate.time)
        _selectedDate.value = formattedDate

        currentJob?.cancel()

        fetchCurrencyData(formattedDate)
    }

    private fun fetchCurrencyData(date: String) {
        _isLoading.value = true

        currentJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyItem = retrofitPrivate.getCurrencyExchange(date)
                withContext(Dispatchers.Main) {
                    _currencyData.value = currencyItem
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _currencyData.value = null
                    _isLoading.value = false
                    Log.e(TAG, "Error fetching currency data", e)
                }
            }
        }
    }

    companion object {
        const val TAG = "XXX"
    }
}

