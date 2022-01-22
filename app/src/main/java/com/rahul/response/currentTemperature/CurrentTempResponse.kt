package com.rahul.response.currentTemperature


import com.google.gson.annotations.SerializedName
data class CurrentTempResponse(

    @SerializedName("list")
    val temperature: List<Temperature>,

)