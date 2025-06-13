package slot.machine.database

import androidx.room.Database
import androidx.room.RoomDatabase
import slot.machine.database.model.Credit
import slot.machine.database.model.CreditDao

@Database(
    entities = [Credit::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun creditDao(): CreditDao
}