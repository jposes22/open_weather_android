package com.test.ui.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel
import com.test.domain.model.model.FavCityModel
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

    private val _favouriteId = MutableStateFlow(settingsSharedPreferencesManager.getFavouriteCityIds())
    var favouriteIds:MutableStateFlow<List<Long>> = _favouriteId

//ACTIVATES WHEN A LIST COMPONENT CHANGES
    val cityList: Flow<List<FavCityModel>?> =
    favouriteIds.transformLatest { cityIds ->
            return@transformLatest cityRepository.findAllIds(cityIds).collect{ citiEntities ->
                emit(cityConverter.toModel(citiEntities))
            }
        }




    //  WHEN CLICKED..... SHOULD EXPAND MORE DATA TO SHOW ABOUT SELECTED CITY
    fun selectedCity(citySelected: FavCityModel) {

    }
}