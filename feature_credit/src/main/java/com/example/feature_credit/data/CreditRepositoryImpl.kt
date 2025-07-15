package com.example.feature_credit.data

import com.example.feature_credit.domain.repository.CreditRepository
import slot.machine.database.model.Credit
import slot.machine.database.model.CreditDao

class CreditRepositoryImpl(
    private val creditDao: CreditDao
) : CreditRepository {
    override fun getCredit(): Credit {
        return creditDao.getCredit()
    }

    override suspend fun changeCredit(change: Int) {
        creditDao.changeCredit(change)
    }
}