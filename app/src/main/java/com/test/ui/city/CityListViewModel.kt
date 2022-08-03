package com.test.ui.city

import androidx.lifecycle.ViewModel
import com.test.domain.model.entity.CityEntity
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val cityRepository: CityRepository,
): ViewModel() {

    private val _cityName = MutableStateFlow("")
    var cityName:MutableStateFlow<String> = _cityName

    val cityList: Flow<List<CityEntity>?> =
        cityName.transformLatest { cityNameSearch ->
                return@transformLatest cityRepository.findAllByName(cityNameSearch).collect{
                    emit(it)
            }
        }
}