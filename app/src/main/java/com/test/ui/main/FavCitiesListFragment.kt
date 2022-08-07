package com.test.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.R
import com.test.databinding.FragmentFavCitiesBinding
import com.test.ui.city.CityListViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FavCitiesListFragment : Fragment() {

    private var _binding: FragmentFavCitiesBinding? = null
//    VIEWMODEL MUST CHANGE TO FavCitiesViewModel
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
//         INFLATES BINDING?
        _binding = FragmentFavCitiesBinding.inflate(inflater, container, false)
//        BINDS VIEWMODEL AND SOME SHIT NAMED LIFECYCLE
        _binding!!.viewModel = viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner

        val recyclerView = binding.recyclerViewFavCitiesList
//        GIVES RECYCLER VIEW LINEAR LAYOUT PROPIERTIES¿?¿?¿?
        recyclerView.layoutManager = LinearLayoutManager(context)


//        MAGIC THAT WORKS AND NO-ONE KNOWS WHY
        adapter = FavCitiesRecyclerViewAdapter(context!!, citySelected = {viewModel.selectedCity(it)})
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

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_CityListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}