package com.test.ui.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.R
import com.test.databinding.FragmentFavCitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FavCitiesListFragment : Fragment() {

    private var _binding: FragmentFavCitiesBinding? = null
    private val viewModel: FavCitiesViewModel by viewModels()
    private lateinit var adapter: FavCitiesRecyclerViewAdapter
    //     This property is only valid between onCreateView and
//     onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavCitiesBinding.inflate(inflater, container, false)
        _binding!!.viewModel = viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        val recyclerView = binding.recyclerViewFavCitiesList
        //        GIVES RECYCLER VIEW LINEAR LAYOUT PROPERTIES¿?¿?¿?
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FavCitiesRecyclerViewAdapter(context!!, citySelected = {viewModel.selectedCity(it)})

//        OPEN CityDetailFragment on city click
        adapter = FavCitiesRecyclerViewAdapter(context!!, citySelected = {
                val bundle = bundleOf("cityId" to it.id)
                findNavController().navigate(R.id.action_FavCitiesListFragment_to_CityDetailFragment, bundle)
//                viewModel.citySelected(it)
            }
        )

        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cityList.collect {
                    adapter.submitList(it)
                }
            }
        }

        return binding.root

    }
    //NAVIGATION FUN MAKES WORK RES.NAVIGATION.NAV_GRAPH
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_FavCitiesListFragment_to_CityDetailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}