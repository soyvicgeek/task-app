package com.tecjiquilpan.pendienteslist.data.repository

import com.tecjiquilpan.pendienteslist.data.local.room.ScheduleDataBase
import com.tecjiquilpan.pendienteslist.data.local.room.dao.ScheduleDao
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScheduleRepository {
    private val context = App.getContext()
    private val localDataSource: ScheduleDao =
        ScheduleDataBase.getInstance(context).likeAndInterestsDao()

    suspend fun addSchedule(data: ScheduleEntity) {
        withContext(Dispatchers.IO) {
            localDataSource.addSchedule(data)
        }
    }

    suspend fun getAllSchedule(): List<ScheduleEntity> {
        return withContext(Dispatchers.IO) {
            localDataSource.getAllSchedule()
        }
    }

    suspend fun deleteSchedule(id: String) {
        withContext(Dispatchers.IO) {
            localDataSource.deleteSchedule(id)
        }
    }

    suspend fun updateSchedule(update: ScheduleEntity) {
        localDataSource.updateSchedule(
            update.id,
            update.title,
            update.description,
            update.date,
            update.hour
        )
    }
}
