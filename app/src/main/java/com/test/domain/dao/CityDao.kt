package com.test.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.test.domain.model.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao : BaseDao<CityEntity> {

    @Query("select * from cityentity")
    fun getAll(): Flow<CityEntity>

    @Query("select * from cityentity where name LIKE '%' || :name || '%' order by name LIMIT 20")
    fun findAllByName(name: String): Flow<List<CityEntity>>

    @Query("select * from cityentity where id = :id")
    fun findById(id: Long): Flow<CityEntity>

    @Query("select * from cityentity where id in (:ids)")
    fun findAllByIds(ids: List<Long>): Flow<List<CityEntity>>

}