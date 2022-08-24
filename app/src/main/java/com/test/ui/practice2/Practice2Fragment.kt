package com.test.ui.practice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.databinding.FragmentPractice2Binding
import kotlinx.coroutines.launch


class Practice2Fragment : Fragment() {

    private lateinit var _binding: FragmentPractice2Binding
    private val binding get() = _binding!!

    private val viewModel: Practice2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPractice2Binding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.fruitList.collect{ listFruits ->
                        binding.textViewPractice2.text = listFruits.joinToString (",")
                    }
                    viewModel.fruitNumber.collect{
                        binding.textViewPractice2CountOfFruits.text = it.toString()
                    }
                }
            }
        }
        return binding.root
    }

}