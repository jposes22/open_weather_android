package com.test.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.test.databinding.FragmentCityListBinding
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class CityListFragment : Fragment() {
    private var _binding: FragmentCityListBinding? = null

    private val viewModel: CityListViewModel by viewModels()
    private lateinit var adapter: CityRecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.viewModel = viewModel
        val recyclerView = binding.cityListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CityRecyclerViewAdapter()
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cityList.collect {
                    adapter.submitList(it)
                }
            }
        }
        return binding.root
/*
        // Set the adapter


        return view*/
    }
}