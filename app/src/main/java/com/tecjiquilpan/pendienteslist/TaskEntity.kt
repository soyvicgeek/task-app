package com.tecjiquilpan.pendienteslist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "pendientes")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "taskId")
    val id : String,
    @ColumnInfo(name = "taskTitle")
    val title : String,
    val description : String,
    val date : Date,
    val time : Date
)
