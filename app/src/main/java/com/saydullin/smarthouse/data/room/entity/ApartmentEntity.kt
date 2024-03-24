package com.saydullin.smarthouse.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saydullin.smarthouse.data.model.LocationData

@Entity
data class ApartmentEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val previewImage: String,
    val imagesId: Int,
    val views: Int,
    val likes: Int,
    val publishedDate: Long,
    val isApproved: Boolean,
    val isHidden: Boolean,
    val userId: Int,
    val email: String,
    val reviewId: Int,
    val location: LocationData,
)


