package com.tecjiquilpan.pendienteslist.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App
            private set
    }

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    fun getContext(): Context {
        return instance.context
    }
}