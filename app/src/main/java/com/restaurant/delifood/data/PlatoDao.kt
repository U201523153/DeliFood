package com.restaurant.delifood.data

import androidx.room.*
import com.restaurant.delifood.model.PlatoDto

@Dao
interface PlatoDao {

    @Insert
    fun insert(platoDto: PlatoDto) : Long

    @Update
    fun update(platoDto: PlatoDto)

    @Delete
    fun delete(platoDto: PlatoDto)

    @Query("select * from platos order by _id desc")
    fun obtenerPlatos():List<PlatoDto>

    @Query("select * from platos where _id =:id order by _id desc")
    fun obtenerPlatosxId(id:String):PlatoDto
}