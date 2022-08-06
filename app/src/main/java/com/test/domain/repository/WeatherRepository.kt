package com.test.domain.repository

import com.test.domain.model.remote.response.WeatherResponse
import com.test.domain.remote.BaseRemoteApi
import com.test.domain.remote.RemoteExceptions
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
    private val baseRemoteApi: BaseRemoteApi,
) : BaseRepository() {

    @Throws(RemoteExceptions::class)
    suspend fun getWeatherByCityId(cityId: Long): WeatherResponse {
        try {
            return baseRemoteApi.getWeatherByCityId(cityId)
        } catch (e: Exception) {
            throw handleException(e)
        }
    }


}
