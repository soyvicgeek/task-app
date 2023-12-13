package com.tecjiquilpan.pendienteslist.ui.schedule

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.AddScheduleUseCase
import com.tecjiquilpan.pendienteslist.data.repository.ScheduleRepository
import com.tecjiquilpan.pendienteslist.domain.ScheduleUseCase
import com.tecjiquilpan.pendienteslist.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel(): ViewModel() {
    private val context = App.instance.getContext()
    private val addScheduleUseCase = AddScheduleUseCase()

    private val _addSchedule = MutableLiveData<CreationExtras.Empty>()
    val addSchedule: LiveData<CreationExtras.Empty> = _addSchedule

    private val repositoryLocal = ScheduleRepository(context.applicationContext as Application)
    val getSchedule = repositoryLocal.getSchedule()

}