package com.tecjiquilpan.pendienteslist.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = ScheduleEntity.TABLE_NAME)
data class ScheduleEntity(
    @ColumnInfo(name = "title") @NotNull val title: String,
    @ColumnInfo(name = "description") @NotNull val description: String,
    @ColumnInfo(name = "id") @NotNull val id: Int,
    @ColumnInfo(name = "date") @NotNull val date: String,
    @ColumnInfo(name = "hour") @NotNull val hour: String
) {
    companion object {
        const val TABLE_NAME = "schedule_detail"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "schedule_id")
    var scheduleId: Int = 0
}

