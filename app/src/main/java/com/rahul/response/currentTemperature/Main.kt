package com.rahul.response.currentTemperature

import com.google.gson.annotations.SerializedName


data class Main(

    @SerializedName("temp")
    val temp: Double,

)