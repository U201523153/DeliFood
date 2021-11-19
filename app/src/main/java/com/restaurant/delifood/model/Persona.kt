package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName

data class Persona(
    @SerializedName("_id")
    val _id:String,

    @SerializedName("nombres")
    val nombres:String,

    @SerializedName("apellidosPaternos")
    val apellidosPaternos:String,

    @SerializedName("apellidosMaternos")
    val apellidosMaternos:String,

    @SerializedName("correo")
    val correo:String
)
