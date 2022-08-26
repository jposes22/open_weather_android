package com.test.ui.practice3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.test.databinding.FragmentPractice3Binding

class Practice3Fragment : Fragment(){
    private lateinit var _binding: FragmentPractice3Binding
    private val binding get() = _binding!!
    private val viewModel: Practice3ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPractice3Binding.inflate(inflater,container,false)

        viewModel.initialValues()
        refreshList()

        binding.btnAddFruit.setOnClickListener{
            viewModel.addFruit(binding.editTextNewFruitPr3.text.toString())
            refreshList()
        }
        binding.btnDeleteFruit.setOnClickListener{
            viewModel.deleteLastFruit()
            refreshList()
        }
        binding.btnDeleteFruitByName.setOnClickListener{
            viewModel.deleteFruitByName(binding.editTextNewFruitPr3.text.toString())
            refreshList()
        }

        return binding.root
    }
    fun refreshList(){
        binding.textViewList.text = viewModel.fruitList.toString()
    }
}