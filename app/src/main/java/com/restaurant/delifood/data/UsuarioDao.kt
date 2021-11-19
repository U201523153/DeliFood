package com.restaurant.delifood.data

import androidx.room.*
import com.restaurant.delifood.model.PlatoDto
import com.restaurant.delifood.model.Usuario
import com.restaurant.delifood.model.UsuarioDto

@Dao
interface UsuarioDao {

    @Insert
    fun insert(usuarioDto: UsuarioDto)

    @Delete
    fun delete(usuarioDto: UsuarioDto)

    @Query("select * from usuarios")
    fun obtenerUsuario():List<UsuarioDto>
}