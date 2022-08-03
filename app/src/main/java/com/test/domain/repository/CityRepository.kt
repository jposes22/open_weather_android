package com.test.domain.repository

import android.content.Context
import androidx.compose.animation.core.FloatTweenSpec
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.R
import com.test.domain.converter.CityConverter
import com.test.domain.dao.CityDao
import com.test.domain.model.dto.CityDto
import com.test.domain.model.entity.CityEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import org.json.JSONException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CityRepository @Inject constructor(
    private val cityDao: CityDao,
    private val cityConverter: CityConverter,
    @ApplicationContext private val context: Context
) {
    fun updateCityOnDatabase() {
        cityDao.insert(cityConverter.toEntity(readJSONFromAsset()))
    }

    fun findAllByName(name:String): Flow<List<CityEntity>>{
        return cityDao.findAllByName(name)
    }

    private fun readJSONFromAsset(): List<CityDto> {
        try {
            val itemType = object : TypeToken<List<CityDto>>() {}.type
            val gson = Gson().newBuilder().create()
            return gson.fromJson(
                context.resources.openRawResource(
                    R.raw.citylist
                ).bufferedReader().use { it.readText() }, itemType
            )
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return emptyList()
    }
}
