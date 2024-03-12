package com.saydullin.smarthouse.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saydullin.smarthouse.data.converter.IntListConverter
import com.saydullin.smarthouse.data.converter.LocationDataConverter
import com.saydullin.smarthouse.data.room.dao.ApartmentDao
import com.saydullin.smarthouse.data.room.entity.ApartmentEntity

@Database(
    entities = [
        ApartmentEntity::class,
               ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    IntListConverter::class,
    LocationDataConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apartmentDao(): ApartmentDao

}


