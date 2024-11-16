package com.example.privatbankcurrencies.domain

data class CurrencyItem (
    val baseCcy: String?,
    val buy: String?,
    val ccy: String?,
    val sale: String?,
    var isChosen: Boolean = false
)