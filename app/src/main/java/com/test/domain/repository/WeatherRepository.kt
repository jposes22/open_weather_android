package com.test.domain.repository

import com.test.domain.converter.WeatherConverter
import com.test.domain.dao.WeatherDao
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.remote.BaseRemoteApi
import com.test.domain.remote.RemoteExceptions
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
    private val baseRemoteApi: BaseRemoteApi,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val weatherConverter: WeatherConverter,
    private val weatherDao: WeatherDao
) : BaseRepository() {

    @Throws(RemoteExceptions::class)
    suspend fun getWeatherByCityIdAndSave(cityId: Long): WeatherEntity {
        try {
            val weatherEntity = weatherConverter.toEntity(baseRemoteApi.getWeatherByCityId(cityId))
            weatherDao.insert(weatherEntity)
            return weatherEntity
        } catch (e: Exception) {
            throw handleException(e)
        }
    }


    suspend fun updateAllFavourite() {
        sharedPreferencesManager.getFavouriteCityIds().forEach { cityId:Long ->
            try {
                getWeatherByCityIdAndSave(cityId)
            }catch (e: Exception){

            }
        }

    }


}
