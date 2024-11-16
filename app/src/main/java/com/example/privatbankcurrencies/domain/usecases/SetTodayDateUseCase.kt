package com.example.privatbankcurrencies.domain.usecases

import com.example.privatbankcurrencies.domain.Repository

class SetTodayDateUseCase(
    private val repository: Repository
) {
    fun setTodayDate(){
        repository.setTodayDate()
    }
}