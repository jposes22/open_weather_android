package com.test.ui.practice3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Practice3ViewModel @Inject constructor() : ViewModel(){

    var fruitList =  mutableListOf<String>()

    fun initialValues(){
        fruitList.add(0,"Banana")
        fruitList.add("Tomato")
    }
    fun addFruit(fruit:String){
        fruitList.add(fruit)
    }
    fun deleteLastFruit(){
        fruitList.removeLast()
    }

    fun deleteFruitByName(fruit : String){
//        fruitList.indexOf(fruit)
//        Creo que kotlin no necesita que saque el index para eliminar
        fruitList.remove(fruit)
    }
}