package com.tecjiquilpan.pendienteslist.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository
import com.tecjiquilpan.pendienteslist.ui.App

class MainViewModel: ViewModel() {
    private val context = App.instance.getContext()
    private val repositoryLocal = ScheduleRepository(context.applicationContext as Application)
    val schedule = repositoryLocal.getSchedule()

    private fun saveScheduleDetail(scheduleEntity: ScheduleEntity) {
        repositoryLocal.insert(scheduleEntity)
    }
}