package com.saydullin.smarthouse.domain.repository

interface AuthRepository {

    suspend fun signIn()

    suspend fun logIn()

    suspend fun logOut()

    suspend fun deleteAccount()

}