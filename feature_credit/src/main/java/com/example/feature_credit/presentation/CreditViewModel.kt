package com.example.feature_credit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_credit.domain.UpdateCreditUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditViewModel @Inject constructor(
    private val updateCreditUseCase: UpdateCreditUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreditState())
    val uiState: StateFlow<CreditState> = _uiState.asStateFlow()

    fun onUpdateAddedCredit(amount: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                addedCredit = currentState.addedCredit + amount
            )
        }
    }

    fun onUpdateCredit() = viewModelScope.launch(Dispatchers.IO) {
        updateCreditUseCase(_uiState.value.addedCredit)
        _uiState.update { currentState ->
            currentState.copy(
                currentCredit = _uiState.value.currentCredit + _uiState.value.addedCredit,
                addedCredit = 0
            )
        }
    }

    fun onReset() {
        _uiState.update { currentState ->
            currentState.copy(
                addedCredit = 0
            )
        }
    }
}