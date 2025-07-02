package com.example.feature_credit.domain

import com.example.feature_credit.domain.repository.CreditRepository
import slot.machine.database.model.Credit

class UpdateCreditUseCase(
    private val creditRepository: CreditRepository
) {
    suspend operator fun invoke(amount: Int) {
        creditRepository.changeCredit(Credit(amount = amount))
    }
}