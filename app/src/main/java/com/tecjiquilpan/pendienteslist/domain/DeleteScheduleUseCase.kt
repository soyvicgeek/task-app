package com.tecjiquilpan.pendienteslist.domain

import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository


class DeleteScheduleUseCase {
    private val repository = ScheduleRepository()

    suspend operator fun invoke(id: String) {
        return repository.deleteSchedule(id)
    }
}
