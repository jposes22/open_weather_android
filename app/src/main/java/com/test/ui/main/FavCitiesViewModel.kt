package com.test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
THIS CLASS MUST CALL DATA FROM CITIES BD AND MAKE RECYCLERVIEW CAPABLE OF OBTAIN THIS DATA
 */
@HiltViewModel
class FavCitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val settingsSharedPreferencesManager: SharedPreferencesManager,
    private val cityConverter: CityConverter
): ViewModel(){
//I DONT GET WHY I NEED _FAVOURITEIDS BUT THE CODE DOOESNT WORK WITHOUT IT BUT REFERENCES DONT WORK NEITHER
    private val _cityName = MutableStateFlow("")
    var cityName:MutableStateFlow<String> = _cityName
    private val _favouriteIds = MutableStateFlow(settingsSharedPreferencesManager.getFavouriteCityIds())
    var favouriteIds:MutableStateFlow<List<Long>> = _favouriteIds

//ACTIVATES WHEN A LIST COMPONENT CHANGES
    private val _cityList: Flow<List<CityEntity>?> =
        cityName.transformLatest { cityNameSearch ->
            return@transformLatest cityRepository.findAllByName(cityNameSearch).collect{
                emit(it)
            }
        }
//REFRESH THE VIEW WHEN THE LISTED ITEM CHANGES
    val cityList:Flow<List<CityListModel>> =
        _cityList.combine(favouriteIds){ cities, ids ->
            return@combine cities?.let { cityConverter.toModel(it,ids) } ?: emptyList()
        }

    private val _favouriteIds = MutableStateFlow(settingsSharedPreferencesManager.getFavouriteCityIds())


    //  WHEN CLICKED..... SHOULD EXPAND MORE DATA TO SHOW ABOUT SELECTED CITY
    fun selectedCity(citySelected: CityListModel) {

    }
}