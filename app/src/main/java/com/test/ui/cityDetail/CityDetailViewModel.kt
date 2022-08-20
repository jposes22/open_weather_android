package com.test.ui.cityDetail

import androidx.lifecycle.ViewModel
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.model.model.FavCityModel
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class CityDetailViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val cityConverter: CityConverter
        ): ViewModel() {

//    fun getDetails(id:Long): Flow<FavCityModel> {
//        return cityRepository.findAllById(id).combine(weatherRepository.findAllById(id)) {
//                        cityEntities, weatherEntities ->
//                    return@combine cityConverter.toFavModel(cityEntities, weatherEntities)
//                }
//    }

//    private fun getCityDetails(id: Long): Flow<CityEntity> {
//        return cityRepository.findAllById(id)
//    }
//
//    private fun getCityWeather(id: Long): Flow<WeatherEntity> {
//        return weatherRepository.findAllById(id)
//    }

    fun getDetails(id: Long): Flow<FavCityModel> {
        val cityData: Flow<CityEntity> = cityRepository.findAllById(id)
        val weatherData: Flow<WeatherEntity> = weatherRepository.findAllById(id)
        return combine(cityData, weatherData, id)
    }

    private fun combine(flows: Flow<CityEntity>, flow: Flow<WeatherEntity>, id: Long): Flow<FavCityModel> {
        return  cityRepository.findAllById(id).combine(weatherRepository.findAllById(id)) {
                cityEntities, weatherEntities ->
            return@combine cityConverter.toFavModel(cityEntities, weatherEntities)
        }
    }


}