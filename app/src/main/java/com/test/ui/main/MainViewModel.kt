package com.test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {

    fun updateDataIfNeeded(){
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getWeatherByCityId(3118848L)
            //Madrid - 1704129
            //Las Rozas de Madrid - 3118848
            //Comunidad de Madrid - 3117732
            //Madrid - 3117735
        }
        if(sharedPreferencesManager.getIsFirstTime()) {
            viewModelScope.launch(Dispatchers.IO) {
                cityRepository.updateCityOnDatabase()
                sharedPreferencesManager.setFirstTime(false)
            }
        }

    }
}