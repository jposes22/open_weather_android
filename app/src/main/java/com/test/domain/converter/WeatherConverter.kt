package com.test.domain.converter

import com.test.domain.model.entity.WeatherEntity
import com.test.domain.model.remote.response.WeatherResponse
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherConverter @Inject constructor(){

    fun toEntity(weatherResponses:List<WeatherResponse>):List<WeatherEntity>{
        return weatherResponses.map { toEntity(it) }
    }

    fun toEntity(weatherResponse: WeatherResponse): WeatherEntity {
        val weatherEntity = WeatherEntity()
        weatherEntity.id = weatherResponse.id
        weatherEntity.name = weatherResponse.name
        weatherEntity.date = weatherResponse.date?.let { Date(it*1000) }
        weatherEntity.temperature = weatherResponse.info?.temperature
        weatherEntity.pressure = weatherResponse.info?.pressure
        weatherEntity.humidity = weatherResponse.info?.humidity
        weatherEntity.maxTemperature = weatherResponse.info?.maxTemperature
        weatherEntity.minTemperature = weatherResponse.info?.minTemperature
        return weatherEntity
    }

}