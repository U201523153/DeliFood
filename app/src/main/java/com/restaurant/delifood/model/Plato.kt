package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Plato(

    @SerializedName("_id")
    val _id: String,

    @SerializedName("avatar")
    val avatar: String,

    @SerializedName("category_id")
    val category_id: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double
) : Serializable