package com.example.privatbankcurrencies.domain

interface Repository {
    //
    fun setTodayDate()
    fun setAnotherDate(date: DateItem)
    fun getExRate(date: DateItem, currency: CurrencyItem)
}