package com.saydullin.smarthouse.domain.model

data class UserData(
    val id: Int = 0,
    val uid: String = "",
    val status: String = "",
    val imageProfile: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val userName: String = "",
    val description: String = "",
    val dateBirth: Long = 0,
    val email: String = "",
    val phoneNumber: String = "",
    val country: String = "",
    val city: String = "",
)


