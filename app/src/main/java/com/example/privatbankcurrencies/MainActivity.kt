package com.example.privatbankcurrencies

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.privatbankcurrencies.databinding.ActivityMainBinding
import com.example.privatbankcurrencies.retrofit.RetrofitPrivate
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()

        viewModel.selectedDate.observe(this) { date ->
            binding.etDate.hint = date
        }

        viewModel.currencyData.observe(this) { currencyData ->
            if (currencyData != null) {
                Log.d(TAG, "onCreate: $currencyData")
            }
        }

        binding.etDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                viewModel.onDateSelected(selectedYear, selectedMonth, selectedDay)
            }, year, month, day).show()
        }
    }
    companion object {
        const val TAG = "XXX"
    }
}
