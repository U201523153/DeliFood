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
    val price: Double,

    @SerializedName("stock")
    val stock: Int,

    @SerializedName("grasas")
    val grasas: Double,

    @SerializedName("grasas_df")
    val grasas_df: Double,

    @SerializedName("calorias")
    val calorias: Double,

    @SerializedName("calorias_df")
    val calorias_df: Double,

    @SerializedName("colesterol")
    val colesterol: Double,

    @SerializedName("colesterol_df")
    val colesterol_df: Double

    ) : Serializable