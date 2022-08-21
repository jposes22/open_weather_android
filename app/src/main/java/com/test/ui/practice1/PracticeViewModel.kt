package com.test.ui.practice1

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel  @Inject constructor(
): ViewModel(){

    val flowExample1 = flow<Int> {
        emit(1)
        delay(1000)
        emit(20)
        delay(2000)
        emit(100)
        delay(5000)
        emit(200)
    }

    val flowExample2:Flow<Float> = flowExample1.transform { value: Int ->
        return@transform   emit(value *1.2f)
    }

    //TODO: you must implement all time sum of flowExample1 and flowExample2 and show on screen
    //val flowExample3:Flow<Float> =

}