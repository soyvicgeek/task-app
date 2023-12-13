package com.tecjiquilpan.pendienteslist.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity

@Dao
interface ScheduleDao {
    @Insert
    suspend fun addSchedule(road: ScheduleEntity)

    @Query("SELECT * FROM ScheduleEntity")
    suspend fun getAllSchedule(): List<ScheduleEntity>

    @Query("DELETE FROM ScheduleEntity WHERE id=:id")
    suspend fun deleteSchedule(id: String)

    @Query("UPDATE ScheduleEntity SET title = :title, description=:description, date=:date, hour = :hour WHERE id = :id")
    suspend fun updateSchedule(
        id: Int,
        title: String?,
        description: String?,
        date: String?,
        hour: String?
    ): Int
}