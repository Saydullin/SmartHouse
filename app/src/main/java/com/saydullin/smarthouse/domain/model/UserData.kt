package com.saydullin.smarthouse.domain.model

data class UserData(
    val id: Int,
    val uid: String,
    val status: String,
    val imageProfile: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val userName: String,
    val description: String,
    val dateBirth: Long,
    val email: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
)