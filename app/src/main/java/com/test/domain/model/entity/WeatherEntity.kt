package com.test.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.test.domain.converter.DateConverter
import java.util.*

@Entity
class WeatherEntity {
    @PrimaryKey
    var cityId : Long? = null
    var name : String? = null
    @TypeConverters(DateConverter::class)
    var date: Date? = null
    var temperature: Float? = null
    var pressure: Int? = null
    var humidity: Int? = null
    var maxTemperature:Float? = null
    var minTemperature:Float? = null
}