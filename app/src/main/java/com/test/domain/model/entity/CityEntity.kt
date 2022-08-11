package com.test.domain.model.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class CityEntity {
    @PrimaryKey
    var id:Long? = null
    var name:String? = null
    var state:String? = null
    var country:String? = null
    var latitude:Float? = null
    var longitude:Float? = null
    var isFavourite:Boolean? = null

}