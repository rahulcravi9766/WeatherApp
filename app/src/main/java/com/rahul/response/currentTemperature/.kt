package com.rahul.response.currentTemperature


import com.google.gson.annotations.SerializedName

data class Temperature (
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,

)