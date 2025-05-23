package com.example.tragaperras.random_number.presentation

import androidx.lifecycle.ViewModel
import com.example.tragaperras.random_number.presentation.GuessOutcomeEvent.RandomNumberGuessed
import com.example.tragaperras.random_number.presentation.GuessOutcomeEvent.RandomNumberHigher
import com.example.tragaperras.random_number.presentation.GuessOutcomeEvent.RandomNumberLower
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

sealed class GuessOutcomeEvent {
    data object RandomNumberHigher: GuessOutcomeEvent()
    data object RandomNumberLower: GuessOutcomeEvent()
    data object RandomNumberGuessed: GuessOutcomeEvent()
}

class RandomNumberViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RandomNumberState())
    val uiState: StateFlow<RandomNumberState> = _uiState.asStateFlow()

    init {
        // generate random number
        newRandomNumber()
    }

    fun newRandomNumber() {
        _uiState.update { currentState ->
            currentState.copy(
                randomNumber = Random.nextInt(0..100)
            )
        }
    }

    fun changeSelectedNumber(newValue: Float) {
        _uiState.update { currentState ->
            currentState.copy(
                guess = newValue.toInt()
            )
        }
    }

    fun submitGuess() {
        val target = _uiState.value.randomNumber ?: return
        val guess = _uiState.value.guess

        _uiState.update { current ->
            current.copy(
                showNumber = (target == guess),
                guessOutcomeEvent = when {
                    target > guess -> RandomNumberHigher
                    target < guess -> RandomNumberLower
                    else           -> RandomNumberGuessed
                }
            )
        }
    }

    fun reset() {
        _uiState.update { currentState ->
            currentState.copy(
                showNumber = false,
                guessOutcomeEvent = null
            )
        }

        newRandomNumber()
    }
}