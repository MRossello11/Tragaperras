package slot.machine.database.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Credit(
    @PrimaryKey
    val id: Int = 1, // only 1 credit entity will exist in the DB for now
    val amount: Int
)

@Dao
interface CreditDao {
    @Query("SELECT * FROM credit LIMIT 1")
    fun getCredit() : Credit

    @Query("UPDATE credit SET amount = amount + :change WHERE ID = 1")
    suspend fun changeCredit(change: Int)
}
