package com.saydullin.smarthouse.di

import android.content.Context
import androidx.room.Room
import com.saydullin.smarthouse.data.converter.IntListConverter
import com.saydullin.smarthouse.data.converter.LocationDataConverter
import com.saydullin.smarthouse.data.repository.apartments.ApartmentsRepositoryImpl
import com.saydullin.smarthouse.data.repository.profile.ProfileDataRepositoryImpl
import com.saydullin.smarthouse.data.room.AppDatabase
import com.saydullin.smarthouse.data.room.dao.ApartmentDao
import com.saydullin.smarthouse.domain.repository.ApartmentsRepository
import com.saydullin.smarthouse.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "SmartHouseDB"
        )
            .allowMainThreadQueries()
            .addTypeConverter(LocationDataConverter())
            .build()
    }

    @Provides
    @Singleton
    fun provideApartmentDao(appDatabase: AppDatabase): ApartmentDao {
        return appDatabase.apartmentDao()
    }

    @Provides
    @Singleton
    fun provideApartmentRepository(): ApartmentsRepository {
        return ApartmentsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return ProfileDataRepositoryImpl()
    }

}


