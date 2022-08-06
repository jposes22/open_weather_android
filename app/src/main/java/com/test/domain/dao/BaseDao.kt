package com.test.domain.dao

import androidx.room.*


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

    @Delete
    fun delete(obj: List<T>)

}

