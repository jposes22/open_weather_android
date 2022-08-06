package com.test.domain.remote

import com.test.base.AppRemoteBase
import com.test.domain.model.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseRemoteApi {

    @GET("2.5/weather")
    suspend fun getWeatherByCityId(
        @Query("id") cityId: Long,
        @Query("appid") apikey: String = AppRemoteBase.ApiId,
        @Query("units") units: String = "metric"
    ): WeatherResponse

}