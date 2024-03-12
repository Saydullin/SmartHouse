package com.saydullin.smarthouse.domain.repository

import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.utils.Resource

interface ApartmentsRepository {

    suspend fun getApartments(): Resource<List<Apartment>>

}