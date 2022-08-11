package com.test.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val settingsSharedPreferencesManager: SharedPreferencesManager,
    private val cityConverter: CityConverter
): ViewModel() {


    private val _cityName = MutableStateFlow("")
    var cityName:MutableStateFlow<String> = _cityName

    val cityList: Flow<List<CityListModel>?> =
        cityName.transformLatest { cityNameSearch ->
                return@transformLatest cityRepository.findAllByName(cityNameSearch).collect{
                    emit(cityConverter.toCityModel(it))
            }
        }


    //add city id to SharedPreferences when is selected is not fav and remove in other case
    fun selectedCity(citySelected: CityListModel) {
        viewModelScope.launch(Dispatchers.IO){
            citySelected.id?.let { cityRepository.updateAsFavourite(it,!citySelected.isFavourite) }
        }
    }
}