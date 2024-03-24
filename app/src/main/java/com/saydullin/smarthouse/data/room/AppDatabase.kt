package com.saydullin.smarthouse.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saydullin.smarthouse.data.converter.LocationDataConverter
import com.saydullin.smarthouse.data.room.dao.ApartmentDao
import com.saydullin.smarthouse.data.room.entity.ApartmentEntity
import com.saydullin.smarthouse.data.room.entity.ApartmentImagesEntity
import com.saydullin.smarthouse.data.room.entity.ApartmentReviewEntity

@Database(
    entities = [
        ApartmentEntity::class,
        ApartmentImagesEntity::class,
        ApartmentReviewEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    LocationDataConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun apartmentDao(): ApartmentDao

}


