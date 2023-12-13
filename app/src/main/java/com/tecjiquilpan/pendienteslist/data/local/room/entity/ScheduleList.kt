package com.tecjiquilpan.pendienteslist.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ScheduleList(val results: List<Schedule> = listOf())

data class Schedule(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val hour: String = ""
)

@Entity
data class ScheduleListEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "date")
    val date: String = "",
    @ColumnInfo(name = "hour")
    val hour: String = "",
)

fun List<ScheduleListEntity>.toScheduleList(): ScheduleList {
    val resultList = mutableListOf<Schedule>()
    this.forEach { scheduleEntity ->
        resultList.add(scheduleEntity.toSchedule())
    }
    return ScheduleList(resultList)
}

fun ScheduleListEntity.toSchedule(): Schedule = Schedule(
    this.id,
    this.title,
    this.description,
    this.date,
    this.hour
)

fun Schedule.toScheduleEntity(): ScheduleListEntity = ScheduleListEntity(
    this.id,
    this.title,
    this.description,
    this.date,
    this.hour
)