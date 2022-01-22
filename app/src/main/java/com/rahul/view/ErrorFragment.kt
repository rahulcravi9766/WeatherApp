package com.rahul.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.rahul.weatherapp.databinding.FragmentErrorBinding


class ErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorBinding.inflate(inflater, container, false)
        navController = findNavController()

        binding.retryButton.setOnClickListener {
            val action = ErrorFragmentDirections.actionLoadingFragmentToHomeFragment()
            navController.navigate(action)
        }


        return binding.root
    }

}