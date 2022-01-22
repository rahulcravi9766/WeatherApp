package com.rahul.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.TemperatureAdapter
import com.rahul.utils.Resource
import com.rahul.viewModel.HomeViewModel
import com.rahul.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var tempAdapter: TemperatureAdapter
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by viewModels()
    lateinit var recyclerView: RecyclerView




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        navController = findNavController()
        recyclerView = binding.rvTemperature

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                }
                else -> {
                    // No location access granted
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        try {
            viewModel.currentCity.observe(viewLifecycleOwner, Observer { response ->
                showProgressBar()
                hideMainView()
                when (response) {

                    is Resource.Success -> {
                        hideProgressBar()
                        showMainView()
                        binding.textCity.text = response.data?.name
                        val temperature = response.data?.main?.temp?.toInt().toString().plus(" C")
                        binding.textCelsius.text = temperature
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showMainView()
                        val action = HomeFragmentDirections.actionHomeFragmentToLoadingFragment()
                        navController.navigate(action)
                        response.message?.let { message ->
                            Log.i("checking", " An error occurred: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                        hideMainView()
                    }
                }
                hideProgressBar()
            })

        } catch (error: Exception) {
            Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
        }

        try {
            viewModel.currentTemp.observe(viewLifecycleOwner, Observer { response ->
                showProgressBar()
                hideMainView()
                when (response) {

                    is Resource.Success -> {
                        hideProgressBar()
                        showMainView()

                        recyclerView.apply {
                            tempAdapter = TemperatureAdapter(response.data)
                            adapter = tempAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }

                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showMainView()
                        val action = HomeFragmentDirections.actionHomeFragmentToLoadingFragment()
                        navController.navigate(action)
                        response.message?.let { message ->
                            Log.i("checking", " An error occurred: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                        hideMainView()
                    }
                }
                hideProgressBar()
            })

        } catch (error: Exception) {

            Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
        }




        return binding.root
    }


    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showMainView() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideMainView() {
        binding.progressBar.visibility = View.INVISIBLE
    }


}