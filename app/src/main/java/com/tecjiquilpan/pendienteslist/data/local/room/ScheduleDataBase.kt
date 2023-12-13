package com.tecjiquilpan.pendienteslist.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tecjiquilpan.pendienteslist.data.local.room.dao.ScheduleDao
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class], version = 1)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun pokemonDao(): ScheduleDao

    companion object {
        private const val DATABASE_NAME = "score_database"

        @Volatile
        private var INSTANCE: ScheduleDatabase? = null

        fun getDatabase(context: Context): ScheduleDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ScheduleDatabase::class.java,
                "schedule_table"
            ).build()
            return INSTANCE as ScheduleDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}