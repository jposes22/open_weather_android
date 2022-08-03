package com.test.ui.main

import androidx.lifecycle.ViewModel
import com.test.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
): ViewModel() {

}