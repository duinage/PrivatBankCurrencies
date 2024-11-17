package com.example.privatbankcurrencies.item


import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("baseCurrency")
    val baseCurrency: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("purchaseRate")
    val purchaseRate: Double?,
    @SerializedName("saleRate")
    val saleRate: Double?
)