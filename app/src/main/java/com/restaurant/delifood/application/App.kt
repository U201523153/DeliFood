package com.restaurant.delifood.application

import android.app.Application
import com.restaurant.delifood.data.AppDatabase

class App : Application() {

    lateinit var instancia : AppDatabase

    override fun onCreate() {
        super.onCreate()

        instancia = AppDatabase.obtenerInstancia(this)
    }

}