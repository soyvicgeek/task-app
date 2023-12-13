package com.tecjiquilpan.pendienteslist.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val typeLikeMotorcycle: String = "",
    val motorcycleBrand: String = "",
    val motorcycleList: String,
    val contentList: String = "",
    val isSkipIfYouHaveAMotorcycle: Boolean = false,
    val isSkipListMotorcycle: Boolean = false,
    val isSkipTypeBrand: Boolean = false,
    val isSkipContent: Boolean = false,
    val idBrand: Int = 0,
    val creationTime: String = "",
)


