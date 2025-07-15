package com.example.feature_credit.domain

import com.example.feature_credit.domain.repository.CreditRepository
import slot.machine.database.model.Credit

class GetCreditUseCase(
    private val creditRepository: CreditRepository
) {
    operator fun invoke(): Credit {
        return creditRepository.getCredit()
    }
}