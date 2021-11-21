package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName

data class Venta (
    @SerializedName("_id")
    val _id: String,

    @SerializedName("cliente")
    val cliente:Persona,

    @SerializedName("fecha_emision")
    val fecha_emision:String,

    @SerializedName("item")
    val item: List<Plato>
)