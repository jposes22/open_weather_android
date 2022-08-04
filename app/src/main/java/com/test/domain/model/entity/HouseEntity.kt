package com.test.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HouseEntity {
    @PrimaryKey
    var id : Long? = null
    var name : String? = null
}