package com.test.domain.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREFERENCES_KEY_IS_FIRST_TIME = "IS_FIRST_TIME"
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

