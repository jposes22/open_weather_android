package com.test.ui.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.converter.CityConverter
import com.test.domain.model.model.CityListModel
import com.test.domain.model.model.FavCityModel
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
THIS CLASS MUST CALL DATA FROM CITIES BD AND MAKE RECYCLERVIEW CAPABLE OF OBTAIN THIS DATA
 */
@HiltViewModel
class FavCitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val cityConverter: CityConverter
): ViewModel(){

    //ACTIVATES WHEN A LIST COMPONENT CHANGES
    val cityList: Flow<List<FavCityModel>> =
        cityRepository.findAllFavourite().combine(weatherRepository.findAll()) { cityEntities, weatherEntities ->
               return@combine cityConverter.toFavModel(cityEntities,weatherEntities)
            }

//    Opens City details
    fun selectedCity(citySelected: FavCityModel){
        viewModelScope.launch (Dispatchers.IO){

        }
    }

}