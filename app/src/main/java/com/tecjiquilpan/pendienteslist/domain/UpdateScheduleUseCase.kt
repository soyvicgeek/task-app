package com.tecjiquilpan.pendienteslist.domain

import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository

class UpdateScheduleUseCase {

    private val iSLLikeAndInterestsRepository = ScheduleRepository()

    suspend operator fun invoke(update: ScheduleEntity) {
        iSLLikeAndInterestsRepository.updateSchedule(update)
    }
}