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

    suspend fun addLikeAndInterests(road: ScheduleEntity) {
        withContext(Dispatchers.IO) {
            localDataSource.addSchedule(road)
        }
    }

    suspend fun getAllLikeAndInterests(): List<ScheduleEntity> {
        return withContext(Dispatchers.IO) {
            localDataSource.getAllSchedule()
        }
    }

    suspend fun deleteLikeAndInterests() {
        withContext(Dispatchers.IO) {
            localDataSource.deleteSchedule()
        }
    }

    suspend fun updateLikeAndInterests(update: ScheduleEntity) {
        localDataSource.updateSchedule(
            update.id,
            update.typeLikeMotorcycle,
            update.motorcycleBrand,
            update.motorcycleList,
            update.contentList,
            update.isSkipIfYouHaveAMotorcycle,
            update.isSkipListMotorcycle,
            update.isSkipTypeBrand,
            update.isSkipContent,
            update.idBrand,
            update.creationTime
        )
    }
}
