package com.tecjiquilpan.pendienteslist.data.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.tecjiquilpan.pendienteslist.data.local.room.ScheduleDatabase
import com.tecjiquilpan.pendienteslist.data.local.room.dao.ScheduleDao
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity

class ScheduleRepository(application: Application) {
    private val scheduleDao: ScheduleDao = ScheduleDatabase.getDatabase(application).pokemonDao()

    fun insert(pokemon: ScheduleEntity) {
        InsertAsyncTask(scheduleDao).execute(pokemon)
    }

    fun getSchedule(): LiveData<List<ScheduleEntity>> {
        return scheduleDao.getSchedule()
    }

    private class InsertAsyncTask(private val scheduleDao: ScheduleDao) :
        AsyncTask<ScheduleEntity, Void, Void>() {
        override fun doInBackground(vararg pokemons: ScheduleEntity?): Void? {
            for (pokemon in pokemons) {
                if (pokemon != null) scheduleDao.addSchedule(pokemon)
            }
            return null
        }
    }
}
