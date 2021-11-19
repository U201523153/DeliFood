package com.restaurant.delifood.model

import com.google.gson.annotations.SerializedName

data class Categories(

    @SerializedName("active")
    val active:Boolean,

    @SerializedName("_id")
    val _id:String,

    @SerializedName("name")
    val name:String,

    @SerializedName("description")
    val description:String
)
