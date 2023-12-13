package com.tecjiquilpan.pendienteslist.data.local.room

import com.tecjiquilpan.pendienteslist.data.local.room.dao.ScheduleDao
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleList
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleListEntity
import com.tecjiquilpan.pendienteslist.data.local.room.entity.toScheduleList

class ScheduleDataSource(
    private val scheduleDao: ScheduleDao,
) {

    suspend fun getLocalSchedule(): ScheduleList {
        return scheduleDao.getAllSchedule().toScheduleList()
    }

    suspend fun savePokemon(data: ScheduleListEntity) {
        scheduleDao.saveSchedule(data)
    }
}
