package com.example.feature_random_number.random_number.presentation

data class RandomNumberState(
    val randomNumber: Int? = null,
    val guess: Int = 0,
    val credit: Int = 0,
    val showNumber: Boolean = false,
    val textToShow: String? = null,
    val guessOutcomeEvent: GuessOutcomeEvent? = null
)
