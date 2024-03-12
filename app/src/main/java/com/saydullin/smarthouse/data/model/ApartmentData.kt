package com.saydullin.smarthouse.data.model

data class ApartmentData(
    val id: Int,
    val title: String,
    val description: String,
    val previewImage: String,
    val images: List<Int>,
    val views: Int,
    val likes: Int,
    val publishedDate: Long,
    val isApproved: Boolean,
    val isHidden: Boolean,
    val userId: Int,
    val email: String,
    val location: LocationData,
)


