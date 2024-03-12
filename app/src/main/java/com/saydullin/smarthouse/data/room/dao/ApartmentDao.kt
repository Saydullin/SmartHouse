package com.saydullin.smarthouse.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.saydullin.smarthouse.data.room.entity.ApartmentEntity

@Dao
interface ApartmentDao {

    @Query("SELECT * FROM ApartmentEntity")
    fun getAllApartments(): List<ApartmentEntity>

    @Insert
    fun addApartments(apartments: List<ApartmentEntity>)

    @Delete
    fun deleteApartment(vararg apartment: ApartmentEntity)

}


