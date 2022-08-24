package com.test.ui.practice2

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class Practice2ViewModel @Inject constructor(
) : ViewModel() {

    var fruitList = flow<List<String>> {
        emit(listOf("Banana","Tomatoes"))
    }
    //FIX: 1º Implement on view the implementation when you write a fruit name on EditText and press add button then add this new fruit to fruitList
    fun addFruit(newFruit:String){

        fruitList.mapLatest {  }

    }
    //FIX: 2º Implement on view the implementation when you press remove last remove last element of fruitList
    fun removeFruit(){
//        fruitList.
    }
    //FIX: 3º Implement on view the implementation textViewPractice2CountOfFruits show always the number of fruits are on list
    val fruitNumber = flow {
        emit(fruitList.last().count())
    }
}