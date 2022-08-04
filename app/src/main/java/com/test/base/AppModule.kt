package com.test.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.test.domain.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun applicationDatabase(application: Application): AppDatabaseBase {
        return Room.databaseBuilder(
            application,
            AppDatabaseBase::class.java,
            AppDatabaseBase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCityDao(appDatabaseBase: AppDatabaseBase): CityDao {
        return appDatabaseBase.cityDao()
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context : Context): SharedPreferences {
        return context.getSharedPreferences(
            context.packageName.toString() + "_prefs",
            Context.MODE_PRIVATE
        )
    }


}