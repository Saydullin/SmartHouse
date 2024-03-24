package com.saydullin.smarthouse.domain.repository

import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.utils.Resource

interface ApartmentsRepository {

    suspend fun getApartments(): Resource<List<Apartment>>

    suspend fun saveApartment(apartment: Apartment): Resource<Unit>

    suspend fun saveFavouriteApartment(apartmentFavourite: ApartmentFavourite): Resource<Unit>

}