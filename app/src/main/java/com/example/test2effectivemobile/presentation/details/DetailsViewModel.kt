package com.example.test2effectivemobile.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2effectivemobile.domain.models.ProductDetailsItem
import com.example.test2effectivemobile.domain.usecases.LoadPhoneImagesUseCase
import com.example.test2effectivemobile.domain.usecases.LoadProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val loadProductDetailsUseCase: LoadProductDetailsUseCase,private val loadPhoneImagesUseCase: LoadPhoneImagesUseCase): ViewModel() {

    val imageUrlList = MutableLiveData<List<String>>()
    val isImageLoading = MutableLiveData<Boolean>()
    val productDetails = MutableLiveData<ProductDetailsItem?>()

    init {
        loadPhoneImages()
    }

    fun loadProductDetails() {
        isImageLoading.value = true
        viewModelScope.launch {
            val result = loadProductDetailsUseCase.execute()
            productDetails.postValue(result)

        }

    }

    fun loadPhoneImages() {
        isImageLoading.value = true

        viewModelScope.launch {
            val result = loadPhoneImagesUseCase.execute()
            imageUrlList.postValue(result)
            isImageLoading.postValue(false)
        }

    }

}