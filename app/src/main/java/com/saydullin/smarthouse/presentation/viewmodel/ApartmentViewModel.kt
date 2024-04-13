package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.data.model.ApartmentFavourite
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.model.ApartmentUserRate
import com.saydullin.smarthouse.domain.usecase.apartment.AddApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.GetApartmentRatesUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.GetApartmentUseCase
import com.saydullin.smarthouse.domain.usecase.apartment.RateApartmentUseCase
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
    private val rateApartmentUseCase: RateApartmentUseCase,
    private val getApartmentRatesUseCase: GetApartmentRatesUseCase,
) : ViewModel() {

    private val _apartments = mutableStateOf<List<Apartment>?>(null)
    private val _apartmentUserRate = mutableStateOf<List<ApartmentUserRate>?>(null)
    private val _currentApartment = mutableStateOf<Apartment?>(null)
    private val _error = mutableStateOf<StatusCode?>(null)
    val error = _error
    val apartments = _apartments
    val apartmentUserRate = _apartmentUserRate
    val currentApartment = _currentApartment

    fun setCurrentApartment(apartment: Apartment) {
        _currentApartment.value = apartment
    }

    fun getAllApartments() {
        viewModelScope.launch(Dispatchers.IO) {
            val apartmentsRes = getApartmentUseCase.execute()
            _apartments.value = apartmentsRes.data

            if (apartmentsRes.data == null || apartmentsRes is Resource.Error) {
                _error.value = apartmentsRes.statusCode
            }
        }
    }

    fun addApartment(apartment: Apartment) {
        viewModelScope.launch(Dispatchers.IO) {
            val addedRes = addApartmentUseCase.addApartment(apartment)
            if (addedRes is Resource.Error) {
                _error.value = addedRes.statusCode
            }
        }
    }

    fun rateApartment(apartmentUserRate: ApartmentUserRate) {
        viewModelScope.launch(Dispatchers.IO) {
            val ratedRes = rateApartmentUseCase.execute(apartmentUserRate)
            if (ratedRes is Resource.Error) {
                _error.value = ratedRes.statusCode
            }
        }
    }

    fun getApartmentRates(apartment: Apartment) {
        viewModelScope.launch(Dispatchers.IO) {
            val getApartmentRes = getApartmentRatesUseCase.execute(apartment)
            _apartmentUserRate.value = getApartmentRes.data

            if (getApartmentRes.data == null || getApartmentRes is Resource.Error) {
                _error.value = getApartmentRes.statusCode
            }
        }
    }

    fun saveFavourite(apartmentFavourite: ApartmentFavourite) {
        viewModelScope.launch(Dispatchers.IO) {
            val apartmentFavouriteRes = saveApartmentFavouriteUseCase.execute(apartmentFavourite)

            if (apartmentFavouriteRes is Resource.Error) {
                _error.value = apartmentFavouriteRes.statusCode
            }
        }
    }

    fun resetError() {
        _error.value = null
    }

}


