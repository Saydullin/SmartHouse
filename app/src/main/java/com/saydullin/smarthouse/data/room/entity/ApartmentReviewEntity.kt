package com.saydullin.smarthouse.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApartmentReviewEntity(
    @PrimaryKey val id: Int,
    val rateCount: Int,
    val description: String,
    val authorName: String,
    val date: Long,
)


