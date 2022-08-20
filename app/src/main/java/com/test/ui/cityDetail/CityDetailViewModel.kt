package com.test.ui.cityDetail

import androidx.lifecycle.ViewModel
import com.test.domain.converter.WeatherConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CityDetailViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    weatherConverter: WeatherConverter
        ): ViewModel() {

    fun getCityFlow(id:Long): Flow<CityEntity> {
        return cityRepository.findAllById(id)
    }

    fun getCityWeather(id: Long): Flow<WeatherEntity>{
      return weatherRepository.findAllById(id)
    }
    }