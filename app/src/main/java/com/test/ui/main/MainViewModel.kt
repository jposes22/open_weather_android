package com.test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    fun updateDataIfNeeded() {
        viewModelScope.launch(Dispatchers.IO) {
            if (sharedPreferencesManager.getIsFirstTime()) {
                cityRepository.updateCityOnDatabase()
                sharedPreferencesManager.setFirstTime(false)
            }
            weatherRepository.updateAllFavourite()
        }
    }
}