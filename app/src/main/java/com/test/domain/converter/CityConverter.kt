package com.test.domain.converter

import com.test.domain.model.dto.CityDto
import com.test.domain.model.entity.CityEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityConverter @Inject constructor(){

    fun toEntity(citiesDto:List<CityDto>):List<CityEntity>{
        return citiesDto.map { toEntity(it) }
    }

    fun toEntity(citiesDto: CityDto):CityEntity{
        val citieEntity = CityEntity()
        citieEntity.id = citiesDto.id
        citieEntity.name = citiesDto.name
        citieEntity.country = citiesDto.country
        citieEntity.state = citiesDto.state
        citieEntity.latitude = citiesDto.coordinateCity?.latitude
        citieEntity.longitude = citiesDto.coordinateCity?.longitude
        return citieEntity
    }
}