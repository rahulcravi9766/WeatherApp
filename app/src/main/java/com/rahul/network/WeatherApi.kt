package com.rahul.network

import com.rahul.response.currentCityResponse.CurrentCity
import com.rahul.response.currentTemperature.CurrentTempResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val API_KEY = "9b8cb8c7f11c077f8c4e217974d9ee40"
        const val UNITS = "metric"

    }

    @GET("weather?q=Bengaluru")
    suspend fun getCurrentCity(
        @Query("APPID") apiKey: String = API_KEY,
        @Query("units") unit : String = UNITS
    ): Response<CurrentCity>


    @GET("forecast?q=Bengaluru")
    suspend fun getCurrentTemperature(
        @Query("APPID") apiKey: String = API_KEY,
        @Query("units") unit : String = UNITS
    ): Response<CurrentTempResponse>
}