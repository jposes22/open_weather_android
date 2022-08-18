package com.test.ui.cityDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.R
import com.test.databinding.FragmentCityInDetailBinding
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.entity.WeatherEntity
import com.test.domain.repository.CityRepository
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

//        _binding!!.viewModel = viewModel
//        binding.textViewName.text =  arguments?.getLong("cityId")?.toString()?: "DetailsFragment"
//        binding.textViewName.text = cityRepository.findAllById(arguments?.getLong("cityId"))
//        binding.textViewName.text = arguments?.let {
//            viewModel.cityName.toString()
//        }

//        if(arguments?.getLong("cityId")!=null) {
//            viewModel.getCity(arguments?.getLong("cityId")!!)
//                .observe(viewLifecycleOwner, Observer { cityEntity ->
//                    binding.textViewName.text = cityEntity.name
//                    binding.textViewLatitude.text = cityEntity.latitude.toString()
//                    binding.textViewLongitude.text = cityEntity.longitude.toString()
//                })
//        }else{
//                //TODO: ADD A TOAST
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getCityFlow(arguments?.getLong("cityId")!!).collectLatest {
                        cityEntity : CityEntity ->
                    binding.textViewName.text = cityEntity.name
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