package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName

data class Usuario(

    @SerializedName("token")
    val token:String,
    @SerializedName("client_id")
    val client_id:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("clave")
    val clave:String
)