package com.tecjiquilpan.pendienteslist.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.domain.AddScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel: ViewModel() {
    private val addScheduleUseCase = AddScheduleUseCase()

    private val _addSchedule = MutableLiveData<CreationExtras.Empty>()
    val addSchedule: LiveData<CreationExtras.Empty> = _addSchedule

    //se mandan los datos al caso de uso para que los guarde en la base de datos
    fun addSchedule(data: ScheduleEntity) {
        viewModelScope.launch {
            //lo que resiviremos, en este caso solo es de success
            _addSchedule.postValue(CreationExtras.Empty)
            withContext(Dispatchers.IO) {
                addScheduleUseCase(
                    ScheduleEntity( //datos a mandar del formulario
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