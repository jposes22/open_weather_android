package com.test.domain.model.dto

import com.google.gson.annotations.SerializedName

class CityDto {
    @SerializedName("id")
    var id:Long? = null

    @SerializedName("name")
    var name:String? = null

    @SerializedName("state")
    var state:String? = null

    @SerializedName("country")
    var country:String? = null

    @SerializedName("coord")
    var coordinateCity:CoordinateCityDto? = null
}

class CoordinateCityDto{
    @SerializedName("lat")
    var latitude:Float? = null
    @SerializedName("lon")
    var longitude:Float? = null
}