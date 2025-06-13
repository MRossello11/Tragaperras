package com.example.feature_credit.domain.repository

import slot.machine.database.model.Credit

interface CreditRepository {
    fun getCredit(): Credit
    suspend fun changeCredit(newCredit: Credit)
}