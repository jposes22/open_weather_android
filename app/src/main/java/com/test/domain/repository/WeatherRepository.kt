package com.test.domain.repository

import android.content.Context
import com.test.domain.remote.BaseRemoteApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(
    private val baseRemoteApi: BaseRemoteApi,
    @ApplicationContext private val context: Context
) {

    /*
    suspend fun getWeather(page: Int, pageSize: Int): List<WeatherObject> {
        return try {
            //ResourceDto.Success(baseRemoteApi.getAlarms().content)
            ResourceDto.Success(getTemporalMock(page, pageSize))
        } catch (e: Exception) {
            handleException(e)
        }
    }
  */
}
