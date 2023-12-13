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

    @Query("DELETE FROM ScheduleEntity")
    suspend fun deleteSchedule()

    @Query("UPDATE ScheduleEntity SET typeLikeMotorcycle = :typeLikeMotorcycle, motorcycleBrand=:motorcycleBrand, motorcycleList=:motorcycleList, contentList = :contentList, isSkipIfYouHaveAMotorcycle = :isSkipIfYouHaveAMotorcycle, isSkipListMotorcycle = :isSkipListMotorcycle, isSkipTypeBrand = :isSkipTypeBrand, isSkipContent = :isSkipContent, idBrand = :idBrand, creationTime = :creationTime WHERE id = :id")
    suspend fun updateSchedule(
        id: Int,
        typeLikeMotorcycle: String?,
        motorcycleBrand: String?,
        motorcycleList: String?,
        contentList: String?,
        isSkipIfYouHaveAMotorcycle: Boolean?,
        isSkipListMotorcycle: Boolean?,
        isSkipTypeBrand: Boolean?,
        isSkipContent: Boolean?,
        idBrand: Int?,
        creationTime: String?
    ): Int
}