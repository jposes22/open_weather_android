package com.test.domain.repository

import com.test.domain.converter.WeatherConverter
import com.test.domain.dao.CityDao
import com.test.domain.dao.WeatherDao
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.preferences.SharedPreferencesManager
import com.test.domain.remote.BaseRemoteApi
import com.test.domain.remote.RemoteExceptions
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
    private val baseRemoteApi: BaseRemoteApi,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val weatherConverter: WeatherConverter,
    private val weatherDao: WeatherDao,
    private val cityDao: CityDao
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


    fun findAll(): Flow<List<WeatherEntity>> {
        return weatherDao.findAll()
    }


    suspend fun updateAllFavourite() {
        //make coroutinescope and async to increase performance, and execute all in parallel
        coroutineScope {
            cityDao.findAllFavourite().collectLatest { favouriteCities ->
                favouriteCities.forEach { favouriteCity ->
                    try {
                        async{
                            favouriteCity.id?.let { getWeatherByCityIdAndSave(it) }
                        }
                    }catch (e: Exception){

                    }
                }

            }
        }


    }



}
