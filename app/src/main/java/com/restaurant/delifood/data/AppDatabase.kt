package com.restaurant.delifood.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.restaurant.delifood.model.PlatoDto
import com.restaurant.delifood.model.Usuario
import com.restaurant.delifood.model.UsuarioDto

@Database(entities = [PlatoDto::class, UsuarioDto::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    //Definir todos los DAOs que tiene mi aplicacion
    abstract fun platoDao() : PlatoDao
    abstract fun usuarioDao() : UsuarioDao

    companion object{

        var instancia : AppDatabase? = null

        fun obtenerInstancia(context:Context) : AppDatabase {

            if(instancia==null){
                instancia = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "BDDeliFood"
                ).allowMainThreadQueries().build()
            }
            return instancia as AppDatabase
        }
    }
}