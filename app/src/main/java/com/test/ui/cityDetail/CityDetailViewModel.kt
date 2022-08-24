package com.test.ui.cityDetail

import androidx.lifecycle.ViewModel
import com.test.domain.converter.CityConverter
import com.test.domain.model.model.FavCityModel
import com.test.domain.repository.CityRepository
import com.test.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.lastOrNull
import javax.inject.Inject

@HiltViewModel
class CityDetailViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val cityConverter: CityConverter
        ): ViewModel() {

    fun getDetails(id:Long): Flow<FavCityModel> {
        return cityRepository.findAllById(id).combine(weatherRepository.findAllById(id)) {
                        cityEntities, weatherEntities ->
                    return@combine cityConverter.toFavModel(cityEntities, weatherEntities)
                }
    }

suspend fun getDetailsMerge(id: Long):Flow<FavCityModel> {
    val number1 = flow { emit(cityRepository.findAllById(id).lastOrNull()) }
    val number2 = flow { emit(weatherRepository.findAllById(id).lastOrNull()) }

//    i dont know the problem
//    val sumValue = flow<FavCityModel> {cityConverter.toFavModel(number1.last(), number2.last()) }

//    TYPE MISMATCH toFavModel returns FavCityModel TRYING BELOW
//    val sumValue:FavCityModel = cityConverter.toFavModel(number1.last(),number2.last())

//    ONLY A PIECE
//    number1.combine(number2){number1, number2 ->
//        cityConverter.toFavModel(number1, number2)
//    }

//    SHOULD WORK??? DOESNT DO
    val sumValue:Flow<FavCityModel> = number1.combine(number2){number1, number2 ->
        cityConverter.toFavModel(number1, number2)
    }

//    COPY PASTA THAT I DONT KNOW HOW TO APPLY
//    fun <T> Flow<T>.merge(otherFlow: Flow<T>) = flow { this@merge.collect { value -> emit(value) } otherFlow.collect { value -> emit(value) } }

//    NOT IDEA WHAT IT DOES
//    fun <CityEntity, WeatherEntity> combineFlows(flowA : Flow<CityEntity>, flowB : Flow<WeatherEntity>) = flow {
//        var previousB:WeatherEntity?=null
//        combine(flowA, flowB){a,b -> a to b}.collect{(a,b)->
//            if(b==previousB){
//                emit(a to null)
//            }else{
//                previousB = b
//                emit(a to b)
//            }
//        }
//    }
//    val sumValue : Flow<FavCityModel> = combineFlows(number1,number2)
    return sumValue
}

//    MISMATCH TRY
//    suspend fun getDetailsMergeNoFlow(id: Long):FavCityModel {
//        val number1 = flow { emit(cityRepository.findAllById(id).lastOrNull()) }
//        val number2 = flow { emit(weatherRepository.findAllById(id).lastOrNull()) }
//        val sumValue: FavCityModel = cityConverter.toFavModel(number1.last(), number2.last())
//    return sumValue
//}
}