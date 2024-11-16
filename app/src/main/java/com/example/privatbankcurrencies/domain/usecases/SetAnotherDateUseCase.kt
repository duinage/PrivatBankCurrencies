package com.example.privatbankcurrencies.domain.usecases

import com.example.privatbankcurrencies.domain.DateItem
import com.example.privatbankcurrencies.domain.Repository

class SetAnotherDateUseCase(
    private val repository: Repository
) {
    fun setAnotherDate(date: DateItem){
        repository.setAnotherDate(date)
    }
}