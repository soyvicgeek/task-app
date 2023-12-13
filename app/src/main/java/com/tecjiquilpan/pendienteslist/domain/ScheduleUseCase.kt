package com.tecjiquilpan.pendienteslist.domain

import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleLocalRepository

class ScheduleUseCase {
    private val repository = ScheduleLocalRepository()

    suspend operator fun invoke(): List<ScheduleEntity> {
        return repository.getAllLikeAndInterests()
    }
}