package com.rahul.repository

import com.rahul.network.WeatherApi
import com.rahul.response.currentCityResponse.CurrentCity
import com.rahul.response.currentTemperature.CurrentTempResponse
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val api : WeatherApi
){

    suspend fun getCurrentCity() : Response<CurrentCity>{
        return api.getCurrentCity()
    }

    suspend fun getCurrentTemperature() : Response<CurrentTempResponse>{
        return api.getCurrentTemperature()
    }
}