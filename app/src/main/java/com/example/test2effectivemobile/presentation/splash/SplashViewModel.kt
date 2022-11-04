package com.example.test2effectivemobile.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    val isLoaded = MutableLiveData<Boolean>()

    init {
        isLoaded.value = false
        viewModelScope.launch {
            delay(timeMillis = 2000)
            isLoaded.postValue(true)

        }
    }


}