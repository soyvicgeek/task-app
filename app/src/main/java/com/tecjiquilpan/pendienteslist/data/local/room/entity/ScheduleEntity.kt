package com.tecjiquilpan.pendienteslist.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String,
    val hour: String = ""
)


