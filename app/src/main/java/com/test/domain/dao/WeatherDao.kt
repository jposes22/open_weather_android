package com.test.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.test.domain.model.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao: BaseDao<WeatherEntity>{

    @Query("select * from weatherentity")
    fun findAll(): Flow<List<WeatherEntity>>

}