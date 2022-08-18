package com.test.ui.cityDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.domain.converter.CityConverter
import com.test.domain.model.entity.CityEntity
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class CityDetailViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val cityConverter: CityConverter
//    private val weatherRepository: WeatherRepository,
//    private val weatherConverter: WeatherConverter
        ): ViewModel() {

    fun getCity(id:Long):LiveData<CityEntity>{
        return cityRepository.findAllById(id).asLiveData()
    }

    fun getCityFlow(id:Long): Flow<CityEntity> {
        return cityRepository.findAllById(id)
    }
    }