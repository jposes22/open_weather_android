package com.test.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class CityListFragment : Fragment() {
    private val viewModel: CityListViewModel by viewModels()
    private lateinit var adapter: CityRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city_list, container, false)
        // Set the adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.cityListRecyclerView)
        val cityNameSearch = view.findViewById<EditText>(R.id.cityNameEditText)
        cityNameSearch.addTextChangedListener {
            lifecycleScope.launch(Dispatchers.IO) { viewModel.cityName.emit(it.toString())
            }
        }
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

        return view
    }
}