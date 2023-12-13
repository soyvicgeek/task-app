package com.tecjiquilpan.pendienteslist.domain

import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository

class AddScheduleUseCase {
    private val repository = ScheduleRepository()

    suspend operator fun invoke(data: ScheduleEntity) {
        repository.addSchedule(data)
    }
}