package com.test.domain.converter

import com.test.domain.model.dto.CityDto
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel
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

    fun toModel(citiesEntity:List<CityEntity>,favouriteIds:List<Long>):List<CityListModel>{
        return citiesEntity.map { toModel(it,favouriteIds.contains(it.id)) }
    }

    fun toModel(citiesEntity: CityEntity,isFavourite:Boolean):CityListModel{
        val cityListModel = CityListModel()
        cityListModel.id = citiesEntity.id
        cityListModel.name = citiesEntity.name
        cityListModel.countryCode = citiesEntity.country
        cityListModel.stateCode = citiesEntity.state
        cityListModel.latitude = citiesEntity.latitude
        cityListModel.longitude = citiesEntity.longitude
        cityListModel.isFavourite = isFavourite
        return cityListModel
    }
}