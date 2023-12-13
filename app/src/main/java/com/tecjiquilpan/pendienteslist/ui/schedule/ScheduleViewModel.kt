package com.tecjiquilpan.pendienteslist.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.data.repository.AddScheduleUseCase
import com.tecjiquilpan.pendienteslist.domain.ScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel: ViewModel() {
    private val scheduleUseCase = ScheduleUseCase()
    private val addScheduleUseCase = AddScheduleUseCase()

    private val _addSchedule = MutableLiveData<CreationExtras.Empty>()
    val addSchedule: LiveData<CreationExtras.Empty> = _addSchedule

    fun addSchedule(data: ScheduleEntity) {
        viewModelScope.launch {
            _addSchedule.postValue(CreationExtras.Empty)
            withContext(Dispatchers.IO) {
                addScheduleUseCase(
                    ScheduleEntity(
                        title = data.title,
                        description = data.description,
                        date = data.date,
                        hour = data.hour
                    )
                )
                addSchedule
            }
        }
    }
}