package com.test.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.domain.dao.CityDao
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.HouseEntity


@Database(
    entities = [CityEntity::class, HouseEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabaseBase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "weather_database"
    }

    abstract fun cityDao(): CityDao

}