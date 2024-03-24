package com.saydullin.smarthouse.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApartmentImagesEntity(
    @PrimaryKey val id: Int,
    val apartmentId: Int,
    val src: String,
    val alt: String,
)


