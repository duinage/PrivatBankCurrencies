package com.example.privatbankcurrencies.domain.usecases

import com.example.privatbankcurrencies.domain.CurrencyItem
import com.example.privatbankcurrencies.domain.DateItem
import com.example.privatbankcurrencies.domain.Repository

class GetExchangeRateUseCase(
    private val repository: Repository
) {
    fun getExRate(date: DateItem, currency: CurrencyItem){
        return repository.getExRate(date, currency)
    }
}