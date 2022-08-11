package com.test.domain.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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


    fun deleteAll(){
        //for now we must not remove nothing
    }

}

