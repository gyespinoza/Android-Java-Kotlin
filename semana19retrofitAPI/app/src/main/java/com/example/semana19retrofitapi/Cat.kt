package com.example.semana19retrofitapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cat(
    @Expose
    @SerializedName("name") val name:String,
    @Expose
    @SerializedName("origin") val origin:String,
    @Expose
    @SerializedName("description") val description:String,
    @Expose
    @SerializedName("reference_image_id") val reference_image_id: String
)
