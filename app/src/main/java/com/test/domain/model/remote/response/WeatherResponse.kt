package com.test.domain.model.remote.response

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("id")
    var id:Long? = null

    @SerializedName("name")
    var name:String? = null

    @SerializedName("cod")
    var cod:Int? = null

    @SerializedName("coord")
    var coordinatesResponse:WeatherCoordinatesResponse? = null

    @SerializedName("weather")
    var weather:List<WeatherWeatherResponse>? = null

    @SerializedName("base")
    var base:String? = null

    @SerializedName("dt")
    var date:Long? = null

    @SerializedName("main")
    var info:WeatherInfo? = null
}

class WeatherCoordinatesResponse{
    @SerializedName("lon")
    var latitude:Float? = null
    @SerializedName("lat")
    var longitude:Float? = null
}

class WeatherWeatherResponse{
    @SerializedName("id")
    var id:Int? = null
    @SerializedName("main")
    var main:Float? = null
    @SerializedName("icon")
    var icon:Float? = null
}

class WeatherInfo{
    @SerializedName("temp")
    var temperature:Float? = null
    @SerializedName("pressure")
    var pressure:Int? = null
    @SerializedName("humidity")
    var humidity:Int? = null
    @SerializedName("temp_min")
    var minTemperature:Float? = null
    @SerializedName("temp_max")
    var maxTemperature:Float? = null
}

/*
{
    "coord": {
    "lon": 145.77,
    "lat": -16.92
    },
    "weather": [
    {
        "id": 802,
        "main": "Clouds",
        "description": "scattered clouds",
        "icon": "03n"
    }
    ],
    "base": "stations",
    "main": {
    "temp": 300.15,
    "pressure": 1007,
    "humidity": 74,
    "temp_min": 300.15,
    "temp_max": 300.15
    },
    "visibility": 10000,
    "wind": {
        "speed": 3.6,
        "deg": 160
    },
    "clouds": {
        "all": 40
    },
    "dt": 1485790200,
    "sys": {
    "type": 1,
    "id": 8166,
    "message": 0.2064,
    "country": "AU",
    "sunrise": 1485720272,
    "sunset": 1485766550
    },
    "id": 2172797,
    "name": "Cairns",
    "cod": 200
}

 */