package com.tecjiquilpan.pendienteslist.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tecjiquilpan.pendienteslist.data.local.room.dao.ScheduleDao
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class], version = 1)
abstract class ScheduleDataBase : RoomDatabase() {

    abstract fun likeAndInterestsDao(): ScheduleDao

    companion object {
        @Volatile
        private var instance: ScheduleDataBase? = null

        fun getInstance(context: Context): ScheduleDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ScheduleDataBase {
            return Room.databaseBuilder(
                context,
                ScheduleDataBase::class.java,
                "schedule_data_base"
            ).build()
        }
    }
}