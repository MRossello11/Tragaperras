package com.example.tragaperras.random_number.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        // todo: create use case?
        if (_uiState.value.randomNumber!! > _uiState.value.guess) { // todo remove '!!'
            _uiState.update { currentState ->
                currentState.copy(
                    guessOutcomeEvent = GuessOutcomeEvent.RandomNumberHigher,
                )
            }
            resetTextDelayed()
        } else if (_uiState.value.randomNumber!! < _uiState.value.guess) { // todo remove '!!'
            _uiState.update { currentState ->
                currentState.copy(
                    guessOutcomeEvent = GuessOutcomeEvent.RandomNumberLower,
                )
            }
            resetTextDelayed()
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    showNumber = true,
                    guessOutcomeEvent = GuessOutcomeEvent.RandomNumberGuessed,
                )
            }
        }
    }

    private fun resetTextDelayed() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _uiState.update { currentState ->
                currentState.copy(
                    guessOutcomeEvent = null
                )
            }
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