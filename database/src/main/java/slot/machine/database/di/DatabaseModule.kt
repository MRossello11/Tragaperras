package slot.machine.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import slot.machine.database.AppDatabase
import slot.machine.database.model.CreditDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCreditDao(appDatabase: AppDatabase) : CreditDao {
        return appDatabase.creditDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "SlotMachine"
        ).build()
    }
}