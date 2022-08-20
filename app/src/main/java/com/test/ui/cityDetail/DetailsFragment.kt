package com.test.ui.cityDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.databinding.FragmentCityInDetailBinding
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.WeatherEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment (): Fragment() {

    private var _binding: FragmentCityInDetailBinding? = null
    private val viewModel: CityDetailViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityInDetailBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getCityFlow(arguments?.getLong("cityId")!!).collectLatest {
                        cityEntity : CityEntity ->
                    binding.textViewName.text = cityEntity.name
                    binding.textViewLatitude.text = cityEntity.latitude.toString()
                    binding.textViewLongitude.text = cityEntity.longitude.toString()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getCityWeather(arguments?.getLong("cityId")!!).collectLatest {
                        weatherEntity : WeatherEntity ->
                    binding.textViewMaxTemp.text = weatherEntity.maxTemperature.toString()
                    binding.textViewMinTemp.text = weatherEntity.minTemperature.toString()
                    binding.textViewTemp.text = weatherEntity.temperature.toString()
                    binding.textViewHumidity.text = weatherEntity.humidity.toString()
                    binding.textViewDate.text = weatherEntity.date.toString()
                }
            }
        }

            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }