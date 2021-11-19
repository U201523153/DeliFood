package com.restaurant.delifood.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platos")
data class PlatoDto(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val _id: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "category_id")
    val category_id: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "price")
    val price: Double

)