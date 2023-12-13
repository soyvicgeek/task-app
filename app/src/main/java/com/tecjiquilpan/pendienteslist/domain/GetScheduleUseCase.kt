package com.tecjiquilpan.pendienteslist.domain

import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository


class GetScheduleUseCase {
    private val repository = ScheduleRepository()

    suspend operator fun invoke(): List<ScheduleEntity> {
        return repository.getAllSchedule()
    }
}
