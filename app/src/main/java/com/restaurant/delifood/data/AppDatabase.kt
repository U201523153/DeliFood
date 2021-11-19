package com.restaurant.delifood.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.restaurant.delifood.model.PlatoDto

@Database(entities = [PlatoDto::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    //Definir todos los DAOs que tiene mi aplicacion
    abstract fun platoDao() : PlatoDao

    companion object{

        var instancia : AppDatabase? = null

        fun obtenerInstancia(context:Context) : AppDatabase {

            if(instancia==null){
                instancia = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "BDDeliFood"
                ).build()
            }
            return instancia as AppDatabase
        }
    }
}