package com.tecjiquilpan.pendienteslist.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleListEntity

@Dao
interface ScheduleDao {
    @Insert
    fun addSchedule(road: ScheduleEntity)

    @Update
    fun updateToLikeAndInterests(vararg pokemon: ScheduleEntity)

    @Delete
    fun delete(vararg pokemon: ScheduleEntity)

    @Query("SELECT * FROM schedulelistentity")
    suspend fun getAllSchedule(): List<ScheduleListEntity>
    @Query("SELECT * FROM " + ScheduleEntity.TABLE_NAME + " ORDER BY name, url")
    fun getSchedule(): LiveData<List<ScheduleEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSchedule(data: ScheduleListEntity)

}