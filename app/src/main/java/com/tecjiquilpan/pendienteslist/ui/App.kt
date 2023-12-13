package com.tecjiquilpan.pendienteslist.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance

class App : Application() {

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}