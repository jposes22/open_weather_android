package com.test.domain.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREFERENCES_KEY_IS_FIRST_TIME = "IS_FIRST_TIME"
        private const val PREFERENCES_KEY_FAVOURITE_CITY_IDS = "FAVOURITE_CITY_IDS"
    }

    fun getIsFirstTime():Boolean{
        return sharedPreferences.getBoolean(PREFERENCES_KEY_IS_FIRST_TIME, true)
    }

    fun setFirstTime(value: Boolean){
        sharedPreferences.edit().putBoolean(PREFERENCES_KEY_IS_FIRST_TIME,value).apply()
    }

    fun getFavouriteCityIds():List<Long>{
        val jsonCitiesList = sharedPreferences.getString(PREFERENCES_KEY_FAVOURITE_CITY_IDS,null)
        val type = object : TypeToken<List<Long>>(){}.type//converting the json to list
        return Gson().fromJson(jsonCitiesList,type)?: emptyList()//returning the list
    }

    fun setFavouriteCityIds(citiesIds:List<Long>){
        //if we convert to set we remove duplicated ids
        val json = Gson().toJson(citiesIds.toSet().toList())//converting list to Json and convert first
        sharedPreferences.edit().putString(PREFERENCES_KEY_FAVOURITE_CITY_IDS,json).apply()
    }

    fun deleteAll(){
        //for now we must not remove nothing
    }

}

