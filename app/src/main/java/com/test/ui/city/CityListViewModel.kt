package com.test.ui.city

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
    private val _favouriteIds = MutableStateFlow(settingsSharedPreferencesManager.getFavouriteCityIds())
    var favouriteIds:MutableStateFlow<List<Long>> = _favouriteIds

    private val _cityList: Flow<List<CityEntity>?> =
        cityName.transformLatest { cityNameSearch ->
                return@transformLatest cityRepository.findAllByName(cityNameSearch).collect{
                    emit(it)
            }
        }

    val cityList:Flow<List<CityListModel>> =
        _cityList.combine(favouriteIds){ cities, ids ->
            return@combine cities?.let { cityConverter.toModel(it,ids) } ?: emptyList()
        }

    //add city id to SharedPreferences when is selected is not fav and remove in other case
    fun selectedCity(citySelected: CityListModel) {
        val finalListIds:MutableList<Long> = settingsSharedPreferencesManager.getFavouriteCityIds().toMutableList()
        if(settingsSharedPreferencesManager.getFavouriteCityIds().contains(citySelected.id)){
            finalListIds.remove(citySelected.id)
        }else{
            citySelected.id?.let { finalListIds.add(it) }
        }
        settingsSharedPreferencesManager.setFavouriteCityIds(finalListIds)
        viewModelScope.launch { favouriteIds.emit(finalListIds) }
    }
}