package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario(

    @SerializedName("usuario_id")
    val usuario_id:String,

    @SerializedName("email")
    val email:String,

    @SerializedName("clave")
    val clave:String

) : Serializable