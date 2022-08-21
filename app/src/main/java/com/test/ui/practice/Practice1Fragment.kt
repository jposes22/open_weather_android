package com.test.ui.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.databinding.FragmentPractice1Binding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Practice1Fragment : Fragment() {

    private var _binding: FragmentPractice1Binding? = null
    private val binding get() = _binding!!

    private val viewModel: PracticeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPractice1Binding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // why we must use 2 launch
                //https://developer.android.com/topic/libraries/architecture/coroutines?hl=es-419
                launch {
                    viewModel.flowExample1.collect {
                        binding.textViewFlowExample1.text = it.toString()
                    }
                }
                launch {
                    viewModel.flowExample2.collect {
                        binding.textViewFlowExample2.text = it.toString()
                    }
                }
                //Bad practice with multiple collect
                viewModel.flowExample1.collect {
                    binding.textViewFlowExample3.text = it.toString()
                }
                viewModel.flowExample2.collect {
                    binding.textViewFlowExample4.text = it.toString()
                }
                //FINISH this exercise
//                launch {
//                    viewModel.flowExample3.collect{
//
//                    }
//                }


            }
        }

        return binding.root
    }

}