package com.rahul.response.currentCityResponse


import com.google.gson.annotations.SerializedName


data class CurrentCity(
    @SerializedName("base")
    val base: String,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
)