package com.example.privatbankcurrencies.retrofit

import android.util.Log
import com.example.privatbankcurrencies.item.CurrencyItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPrivate {

    private val baseURL = "https://api.privatbank.ua/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ServicePrivate = retrofit.create(ServicePrivate::class.java)

    suspend fun getCurrencyExchange(date: String): CurrencyItem {
        return service.getCurrencyExchange(date)
    }
}