package com.test.domain.dao

import androidx.room.Dao
import com.test.domain.model.entity.WeatherEntity

@Dao
interface WeatherDao: BaseDao<WeatherEntity> {
}