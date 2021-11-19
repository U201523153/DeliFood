package com.restaurant.delifood.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioDto (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name= "usuario_id")
    val usuario_id:String,

    @ColumnInfo(name= "email")
    val email:String,

    @ColumnInfo(name= "clave")
    val clave:String
)