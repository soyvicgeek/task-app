package com.tecjiquilpan.pendienteslist.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.domain.AddScheduleUseCase
import com.tecjiquilpan.pendienteslist.domain.UpdateScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel : ViewModel() {
    private val addScheduleUseCase = AddScheduleUseCase()
    private val updateScheduleUseCase = UpdateScheduleUseCase()

    private val _addSchedule = MutableLiveData<CreationExtras.Empty>()
    val addSchedule: LiveData<CreationExtras.Empty> = _addSchedule

    private val _updateLikeAndInterests = MutableLiveData<CreationExtras.Empty?>()
    val updateLikeAndInterests: LiveData<CreationExtras.Empty?> = _updateLikeAndInterests

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

    fun updateTypeLikeMotorcycle(update: ScheduleEntity) {
        viewModelScope.launch {
            updateScheduleUseCase(update)
        }.invokeOnCompletion { updateNotifications() }
    }

    private fun updateNotifications() {
        viewModelScope.launch {
            _updateLikeAndInterests.postValue(CreationExtras.Empty)
            withContext(Dispatchers.IO) {
                updateLikeAndInterests
            }
        }
    }
}