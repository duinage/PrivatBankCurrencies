package com.example.privatbankcurrencies.retrofit

import com.example.privatbankcurrencies.item.CurrencyItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPrivate {

    // https://api.privatbank.ua/p24api/exchange_rates?date=01.12.2014
    private val baseURL = "https://api.privatbank.ua/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ServicePrivate = retrofit.create(ServicePrivate::class.java)

    fun getCurrencyExchange(date: String, resultCallback: (CurrencyItem?) -> Unit){

        val call = service.getCurrencyExchange(date)
        call.enqueue(object : Callback<CurrencyItem>{
            override fun onResponse(
                call: Call<CurrencyItem>,
                response: Response<CurrencyItem>

            ) {
                resultCallback(response.body())
            }

            override fun onFailure(p0: Call<CurrencyItem>, p1: Throwable) {
                resultCallback(null)
            }

        })
    }
}