package com.example.test2effectivemobile.presentation.homestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.common.constants.Constants
import com.example.test2effectivemobile.domain.models.BestSellerItem
import com.example.test2effectivemobile.domain.models.HotSalesItem
import com.example.test2effectivemobile.domain.usecases.LoadBestSellerUseCase
import com.example.test2effectivemobile.domain.usecases.LoadHotSalesUseCase
import com.example.test2effectivemobile.presentation.homestore.model.ButtonCategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeStoreViewModel @Inject constructor(private val hotSalesUseCase: LoadHotSalesUseCase, private val bestSellerUseCase: LoadBestSellerUseCase) : ViewModel() {

    val buttonCategoriesState = MutableLiveData<List<ButtonCategoryModel>>()
    val hotSales = MutableLiveData<List<HotSalesItem>>()
    val bestSeller = MutableLiveData<List<BestSellerItem>>()
    val isHotSalesLoading = MutableLiveData<Boolean>()
    val isBestSellerLoading = MutableLiveData<Boolean>()

    private fun getUnselectedList(): MutableList<ButtonCategoryModel> {
        return mutableListOf(
            ButtonCategoryModel(
                Constants.PHONES,
                "Phones",
                R.drawable.category_button_not_selected,
                R.drawable.ic_svg_smartphone_dark,
                R.color.text_color
            ),
            ButtonCategoryModel(
                Constants.COMPUTERS,
                "Computers",
                R.drawable.category_button_not_selected,
                R.drawable.ic_svg_computer_dark,
                R.color.text_color
            ),
            ButtonCategoryModel(
                Constants.HEALTH,
                "Health",
                R.drawable.category_button_not_selected,
                R.drawable.ic_svg_health_dark,
                R.color.text_color
            ),
            ButtonCategoryModel(
                Constants.BOOKS,
                "Books",
                R.drawable.category_button_not_selected,
                R.drawable.ic_svg_books_dark,
                R.color.text_color
            ),
            ButtonCategoryModel(
                Constants.PHONES2,
                "Phones2",
                R.drawable.category_button_not_selected,
                R.drawable.ic_svg_smartphone_dark,
                R.color.text_color
            ),
        )
    }

    init {
        selectCategory(Constants.PHONES)
    }


    fun selectCategory(selectedCategory: Int) {
        val list = getUnselectedList()
        when (selectedCategory) {
            Constants.PHONES -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.category_selected_button,
                    icon = R.drawable.ic_svg_smartphone_light,
                    textColor = R.color.color_primary
                )
            }
            Constants.COMPUTERS -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.category_selected_button,
                    icon = R.drawable.ic_svg_computer_light,
                    textColor = R.color.color_primary
                )
            }
            Constants.HEALTH -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.category_selected_button,
                    icon = R.drawable.ic_svg_health_light,
                    textColor = R.color.color_primary
                )
            }
            Constants.BOOKS -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.category_selected_button,
                    icon = R.drawable.ic_svg_books_light,
                    textColor = R.color.color_primary
                )
            }
            Constants.PHONES2 -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.category_selected_button,
                    icon = R.drawable.ic_svg_smartphone_light,
                    textColor = R.color.color_primary
                )
            }
        }
        buttonCategoriesState.value = list
    }

    fun loadHotSales() {
        isHotSalesLoading.value = true
        viewModelScope.launch {
            val result = hotSalesUseCase.execute()
            hotSales.postValue(result)
            isHotSalesLoading.postValue(false)
        }
    }

    fun loadBestSeller() {
        isBestSellerLoading.value = true
        viewModelScope.launch {
            val result = bestSellerUseCase.execute()
            bestSeller.postValue(result)
            isBestSellerLoading.postValue(false)
        }
    }
}
