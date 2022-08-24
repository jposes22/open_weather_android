package com.test.domain.converter

import com.test.domain.model.dto.CityDto
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.model.model.CityListModel
import com.test.domain.model.model.FavCityModel
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

    fun toCityModel(citiesEntity:List<CityEntity>):List<CityListModel>{
        return citiesEntity.map { toCityModel(it) }
    }

    fun toCityModel(citiesEntity: CityEntity):CityListModel{
        val cityListModel = CityListModel()
        cityListModel.id = citiesEntity.id
        cityListModel.name = citiesEntity.name
        cityListModel.countryCode = citiesEntity.country
        cityListModel.stateCode = citiesEntity.state
        cityListModel.latitude = citiesEntity.latitude
        cityListModel.longitude = citiesEntity.longitude
        cityListModel.isFavourite = citiesEntity.isFavourite ?: false
        return cityListModel
    }

    fun toFavModel(citiEntities: List<CityEntity>, weatherEntities:List<WeatherEntity>): List<FavCityModel> {
        return citiEntities.map { cityEntity ->
            toFavModel(cityEntity,weatherEntities.firstOrNull { it.cityId == cityEntity.id })
        }
    }

    fun toFavModel(citiesEntity: CityEntity?,weatherEntity: WeatherEntity?):FavCityModel{
        val favCityModel = FavCityModel()
//        TODO: IF SHOULDNT BE HERE
        if (citiesEntity != null) {
            favCityModel.id = citiesEntity.id
            favCityModel.name = citiesEntity.name
            favCityModel.latitude = citiesEntity.latitude
            favCityModel.longitude = citiesEntity.longitude
            favCityModel.temperature = weatherEntity?.temperature
            favCityModel.humidity = weatherEntity?.humidity
            favCityModel.maxTemperature = weatherEntity?.maxTemperature
            favCityModel.minTemperature = weatherEntity?.minTemperature
            favCityModel.iconCode = weatherEntity?.iconCode
        }

        return favCityModel
    }
}