package com.android.dvtweatherapp.presentation.weather

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.dvtweatherapp.databinding.FragmentWeatherBinding
import com.android.dvtweatherapp.presentation.weather.adapter.DefaultForecastAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val weatherViewModel by viewModel<WeatherViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var forecastAdapter: DefaultForecastAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            weatherViewModel.loadWeather()
        }
        weatherViewModel.weatherState.observe(viewLifecycleOwner) { weatherState ->
            if (weatherState is WeatherState.CurrentWeatherData) {
                setStatusBarColor(weatherState)
            }
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setStatusBarColor(weatherState: WeatherState.CurrentWeatherData) {
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            weatherState.data.weatherType?.backgroundColorRes.let {
                statusBarColor =
                    weatherState.data.weatherType?.statusBarColorRes?.let { colorRes ->
                        resources.getColor(colorRes, null)
                    }!!
            }
        }
    }

    private fun setupViews() {
        forecastAdapter = DefaultForecastAdapter(layoutInflater)
        binding.rvForecast.run {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = forecastAdapter
        }
        binding.viewModel = weatherViewModel
    }
}
