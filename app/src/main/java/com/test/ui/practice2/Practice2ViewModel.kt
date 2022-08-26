package com.test.ui.practice2

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class Practice2ViewModel @Inject constructor(
) : ViewModel() {
// FLOW
    var fruitList = flow<MutableList<String>> {
        emit(listOf("Banana","Tomatoes") as MutableList<String>)
    }
//    LIVEDATA
//    lateinit var fruitList : MutableLiveData<List<String>>

    //FIX: 1º Implement on view the implementation when you write a fruit name on EditText and press add button then add this new fruit to fruitList
    private lateinit var newFruit:MutableStateFlow<String>

    var trueList : Flow<MutableList<String>> = fruitList.transform{ list : MutableList<String> ->
//        return@transform list.add(newFruit.toString())
    }

    //FIX: 2º Implement on view the implementation when you press remove last remove last element of fruitList

//    fun removeFruit(){
//        fruitList.
//    }
    //FIX: 3º Implement on view the implementation textViewPractice2CountOfFruits show always the number of fruits are on list
    val fruitNumber = flow {
        emit(fruitList.last().count())
    }

}