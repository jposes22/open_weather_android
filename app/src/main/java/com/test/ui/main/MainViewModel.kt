package com.test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val sharedPreferencesManager: SharedPreferencesManager,
) : ViewModel() {

    var isVisibleLoader: MutableStateFlow<Boolean> = MutableStateFlow(true)

    fun updateDataIfNeeded() {
        //is first time then insert all info in database and show loader
        if (sharedPreferencesManager.getIsFirstTime()) {
            viewModelScope.launch(Dispatchers.IO) {
                cityRepository.updateCityOnDatabase()
            }
        } else {
            //show next screen
        }

    }
}