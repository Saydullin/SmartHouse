package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.usecase.apartment.AddApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.GetApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.SaveApartmentFavouriteUseCase
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApartmentViewModel @Inject constructor(
    private val getApartmentUseCase: GetApartmentUseCase,
    private val saveApartmentFavouriteUseCase: SaveApartmentFavouriteUseCase,
    private val addApartmentUseCase: AddApartmentUseCase,
) : ViewModel() {

    private val _apartments = mutableStateOf<List<Apartment>?>(null)
    private val _currentApartment = mutableStateOf<Apartment?>(null)
    private val _error = mutableStateOf<StatusCode?>(null)
    val error = _error
    val apartments = _apartments
    val currentApartment = _currentApartment

    fun setCurrentApartment(apartment: Apartment) {
        _currentApartment.value = apartment
    }

    fun getAllApartments() {
        viewModelScope.launch(Dispatchers.IO) {
            val apartmentsRes = getApartmentUseCase.getAllApartments()
            _apartments.value = apartmentsRes.data

            if (apartmentsRes.data == null || apartmentsRes is Resource.Error) {
                _error.value = apartmentsRes.statusCode
            }
        }
    }

    fun addApartment(apartment: Apartment) {
        viewModelScope.launch(Dispatchers.IO) {
            addApartmentUseCase.addApartment(apartment)
        }
    }

    fun saveFavourite(apartmentFavourite: ApartmentFavourite) {
        viewModelScope.launch(Dispatchers.IO) {
            val apartmentFavouriteRes = saveApartmentFavouriteUseCase.execute(apartmentFavourite)
        }
    }

    fun resetError() {
        _error.value = null
    }

}


