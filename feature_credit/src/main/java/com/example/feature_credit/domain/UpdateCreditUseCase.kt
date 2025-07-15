package com.example.feature_credit.domain

import com.example.feature_credit.domain.repository.CreditRepository

class UpdateCreditUseCase(
    private val creditRepository: CreditRepository
) {
    suspend operator fun invoke(change: Int) {
        creditRepository.changeCredit(change)
    }
}