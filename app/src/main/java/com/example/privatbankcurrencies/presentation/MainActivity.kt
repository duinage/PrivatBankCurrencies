package com.example.privatbankcurrencies.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.privatbankcurrencies.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        // current date as a hint
        binding.etDate.hint = dateFormat.format(calendar.time)

        // save time
        var chosenDate: String = dateFormat.format(calendar.time)


        // calling calendar by clicking on edit text (that's why it's not focusable in xml file)
        binding.etDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val newDate = dateFormat.format(selectedDate.time)
                binding.etDate.setText(newDate)

                chosenDate = newDate

            }, year, month, day).show()
        }
    }
}
