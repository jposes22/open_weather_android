package com.test.domain.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.R
import com.test.domain.model.dto.CityDto
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CityRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun updateCityOnDatabase(){
        readJSONFromAsset()
    }
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

    private fun readJSONFromAsset(): List<CityDto> {
        try {
            val itemType = object : TypeToken<List<CityDto>>() {}.type
            val gson = Gson().newBuilder().create()
            val responseDto: List<CityDto> =  gson.fromJson(context.resources.openRawResource(
                R.raw.citylist).bufferedReader().use { it.readText() },itemType)
            return responseDto
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return emptyList()
    }
}
