package com.rahul.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.repository.MainRepository
import com.rahul.response.currentCityResponse.CurrentCity
import com.rahul.response.currentTemperature.CurrentTempResponse
import com.rahul.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository
)  : ViewModel(){

    val currentTemperature = mutableListOf<CurrentCity>()
    val currentCity : MutableLiveData<Resource<CurrentCity>> = MutableLiveData()
    val currentTemp : MutableLiveData<Resource<CurrentTempResponse>> = MutableLiveData()
    val name : MutableLiveData<String> = MutableLiveData()


    init {
        getCurrentCity()
        getCurrentTemperature()
        movieName()

    }
    private fun movieName() = viewModelScope.launch {
        name.postValue(currentTemperature.map {
            it.name

        }.toString())
        Log.i("value",name.toString())
        }



    private fun getCurrentCity() = viewModelScope.launch(Dispatchers.IO) {
        currentCity.postValue(Resource.Loading())
        val response = repository.getCurrentCity()
        currentCity.postValue(handleCityResponse(response))
    }

    private fun getCurrentTemperature() = viewModelScope.launch(Dispatchers.IO) {
        currentTemp.postValue(Resource.Loading())
        val response = repository.getCurrentTemperature()
        currentTemp.postValue(handleTempResponse(response))
    }

    private fun handleCityResponse(response: Response<CurrentCity>) : Resource<CurrentCity> {

        if(response.isSuccessful){
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }

        }else {

            response.body()?.let { resultResponse ->
                return Resource.Error(resultResponse.toString())

            }
        }
        return  Resource.Error(response.message())
    }


    private fun handleTempResponse(response: Response<CurrentTempResponse>) : Resource<CurrentTempResponse> {

        if(response.isSuccessful){
            Log.i("value",response.toString())
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }

        }else {

            response.body()?.let { resultResponse ->
                return Resource.Error(resultResponse.toString())
            }
        }
        return  Resource.Error(response.message())
    }
}