package com.example.tragaperras.random_number.presentation

data class RandomNumberState(
    val randomNumber: Int? = null,
    val selectedNumber: Int = 0,
    val credit: Int = 0,
    val showNumber: Boolean = false,
    val textToShow: String? = null,
    val randomNumberTextEvent: RandomNumberTextEvent? = null
)
