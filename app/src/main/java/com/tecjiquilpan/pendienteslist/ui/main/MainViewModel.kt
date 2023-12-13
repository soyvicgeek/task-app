package com.tecjiquilpan.pendienteslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.domain.DeleteScheduleUseCase
import com.tecjiquilpan.pendienteslist.domain.GetScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private val getLikeAndInterestsUseCase = GetScheduleUseCase()
    private val deleteScheduleUseCase = DeleteScheduleUseCase()

    private val _getScheduleList = MutableLiveData<List<ScheduleEntity>?>()
    val getScheduleList: LiveData<List<ScheduleEntity>?> = _getScheduleList

    private val _deleteSchedule = MutableLiveData<CreationExtras.Empty>()
    val deleteSchedule: LiveData<CreationExtras.Empty?> = _deleteSchedule
    fun geScheduleList() {
        viewModelScope.launch {
            val list = getLikeAndInterestsUseCase.invoke()
            _getScheduleList.postValue(list)
            withContext(Dispatchers.IO) {
                getLikeAndInterestsUseCase
            }
        }
    }

    fun deleteSchedule(id: String) {
        viewModelScope.launch {
            val list = deleteScheduleUseCase.invoke(id)
            _deleteSchedule.postValue(CreationExtras.Empty)
            withContext(Dispatchers.IO) {
                deleteScheduleUseCase
            }
        }
    }
}