package com.saydullin.smarthouse.domain.model

import com.google.firebase.firestore.GeoPoint

data class Apartment @JvmOverloads constructor(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val previewImage: String = "",
    val images: List<String> = listOf(),
    val views: Int = 0,
    val likes: Int = 0,
    val publishedDate: Long = 0,
    val isApproved: Boolean = false,
    val isHidden: Boolean = false,
    val authorUID: Int = 0,
    val email: String = "",
    var location: GeoPoint = GeoPoint(0.0, 0.0),
)


