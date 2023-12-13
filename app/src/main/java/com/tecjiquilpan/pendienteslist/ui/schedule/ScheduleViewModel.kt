package com.tecjiquilpan.pendienteslist.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import com.tecjiquilpan.pendienteslist.data.repository.AddScheduleUseCase
import com.tecjiquilpan.pendienteslist.ui.App

class ScheduleViewModel(): ViewModel() {
    private val context = App.getContext()
    private val addScheduleUseCase = AddScheduleUseCase()

    private val _addSchedule = MutableLiveData<CreationExtras.Empty>()
    val addSchedule: LiveData<CreationExtras.Empty> = _addSchedule

}