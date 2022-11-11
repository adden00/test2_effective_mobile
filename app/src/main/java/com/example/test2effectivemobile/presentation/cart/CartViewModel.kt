package com.example.test2effectivemobile.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2effectivemobile.domain.models.CartInfoItem
import com.example.test2effectivemobile.domain.usecases.LoadCartInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartInfoUseCase: LoadCartInfoUseCase): ViewModel() {
    val cartInfo = MutableLiveData<CartInfoItem?>()
    val isLoading = MutableLiveData<Boolean>()


    init {
        loadCartInfo()

    }

    fun loadCartInfo() {
        isLoading.value = true
        viewModelScope.launch {
            val result = cartInfoUseCase.execute()
            cartInfo.postValue(result)
            isLoading.postValue(false)
        }
    }

}