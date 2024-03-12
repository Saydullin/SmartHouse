package com.saydullin.smarthouse.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saydullin.smarthouse.domain.model.Apartment
import com.saydullin.smarthouse.domain.usecase.GetApartmentUseCase
import com.saydullin.smarthouse.domain.utils.Resource
import com.saydullin.smarthouse.domain.utils.StatusCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApartmentViewModel @Inject constructor(
    private val getApartmentUseCase: GetApartmentUseCase
) : ViewModel() {

    private val _apartments = mutableStateOf<List<Apartment>?>(null)
    private val _error = mutableStateOf<StatusCode?>(null)
    val error = _error
    val apartments = _apartments

    fun getAllRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            val apartmentsRes = getApartmentUseCase.getAllApartments()
            _apartments.value = apartmentsRes.data

            if (apartmentsRes.data != null && apartmentsRes is Resource.Success) {
                _error.value = apartmentsRes.statusCode
            }
        }
    }

}


