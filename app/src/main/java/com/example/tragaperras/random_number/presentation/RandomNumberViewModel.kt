package com.example.tragaperras.random_number.presentation

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tragaperras.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

sealed class RandomNumberTextEvent {
    data object RandomNumberHigher: RandomNumberTextEvent()
    data object RandomNumberLower: RandomNumberTextEvent()
    data object RandomNumberGuessed: RandomNumberTextEvent()
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
                selectedNumber = newValue.toInt()
            )
        }
    }

    fun sendNumber() {
        // todo: create use case?
        if (_uiState.value.randomNumber!! > _uiState.value.selectedNumber) { // todo remove '!!'
            _uiState.update { currentState ->
                currentState.copy(
                    randomNumberTextEvent = RandomNumberTextEvent.RandomNumberHigher,
                )
            }
            resetTextDelayed()
        } else if (_uiState.value.randomNumber!! < _uiState.value.selectedNumber) { // todo remove '!!'
            _uiState.update { currentState ->
                currentState.copy(
                    randomNumberTextEvent = RandomNumberTextEvent.RandomNumberLower,
                )
            }
            resetTextDelayed()
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    showNumber = true,
                    randomNumberTextEvent = RandomNumberTextEvent.RandomNumberGuessed,
                )
            }
        }
    }

    private fun resetTextDelayed() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            _uiState.update { currentState ->
                currentState.copy(
                    randomNumberTextEvent = null
                )
            }
        }
    }

    fun reset() {
        _uiState.update { currentState ->
            currentState.copy(
                showNumber = false,
                randomNumberTextEvent = null
            )
        }

        newRandomNumber()
    }
}