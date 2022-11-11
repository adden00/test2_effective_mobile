package com.example.test2effectivemobile.presentation.homestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.common.constants.Constants
import com.example.test2effectivemobile.domain.models.BestSellerItem
import com.example.test2effectivemobile.domain.models.HotSalesItem
import com.example.test2effectivemobile.domain.usecases.LoadBestSellerUseCase
import com.example.test2effectivemobile.domain.usecases.LoadCartItemsCountUseCase
import com.example.test2effectivemobile.domain.usecases.LoadHotSalesUseCase
import com.example.test2effectivemobile.presentation.homestore.models.ButtonCategoryModel
import com.example.test2effectivemobile.presentation.homestore.models.FilterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeStoreViewModel @Inject constructor(
    private val hotSalesUseCase: LoadHotSalesUseCase,
    private val bestSellerUseCase: LoadBestSellerUseCase,
    private val cartItemsCountUseCase: LoadCartItemsCountUseCase
) : ViewModel() {

    val buttonCategoriesState = MutableLiveData<List<ButtonCategoryModel>>()
    val hotSales = MutableLiveData<List<HotSalesItem>>()
    val bestSeller = MutableLiveData<List<BestSellerItem>>()
    val isHotSalesLoading = MutableLiveData<Boolean>()
    val isBestSellerLoading = MutableLiveData<Boolean>()
    val filterIsShown = MutableLiveData<Boolean>()
    private var allBestSellerItems = listOf<BestSellerItem>()
    val cartItemsCount = MutableLiveData<Int>()

    init {
        selectCategory(Constants.PHONES)
        filterIsShown.value = false
        loadAllInfo()

    }

    fun loadAllInfo() {
        loadBestSeller()
        loadHotSales()
        getCartItemsCount()

    }

    private fun getCartItemsCount() {
        viewModelScope.launch {
            val result = cartItemsCountUseCase.execute()
            cartItemsCount.postValue(result)
        }
    }

    // get filter params and delete items, that shouldn't be included
    fun filterBestSellerItems(filterItem: FilterModel) {
        val result = mutableListOf<BestSellerItem>()
        allBestSellerItems.forEach {
            if (((it.title.contains(filterItem.brand)) or (filterItem.brand == "All"))
                and (it.discount_price >= filterItem.minPrice)
                and (it.price_without_discount <= filterItem.maxPrice)) {
                result.add(it)
            }
        }
        bestSeller.value = result
    }

// returning list of unselected buttons, for selecting only one
    private fun getUnselectedList(): MutableList<ButtonCategoryModel> {
        return mutableListOf(
            ButtonCategoryModel(
                Constants.PHONES,
                "Phones",
                R.drawable.select_category_buttons_not_selected_bg,
                R.drawable.ic_svg_smartphone_dark,
                R.color.secondary_color
            ),
            ButtonCategoryModel(
                Constants.COMPUTERS,
                "Computers",
                R.drawable.select_category_buttons_not_selected_bg,
                R.drawable.ic_svg_computer_dark,
                R.color.secondary_color
            ),
            ButtonCategoryModel(
                Constants.HEALTH,
                "Health",
                R.drawable.select_category_buttons_not_selected_bg,
                R.drawable.ic_svg_health_dark,
                R.color.secondary_color
            ),
            ButtonCategoryModel(
                Constants.BOOKS,
                "Books",
                R.drawable.select_category_buttons_not_selected_bg,
                R.drawable.ic_svg_books_dark,
                R.color.secondary_color
            ),
            ButtonCategoryModel(
                Constants.PHONES2,
                "Phones2",
                R.drawable.select_category_buttons_not_selected_bg,
                R.drawable.ic_svg_smartphone_dark,
                R.color.secondary_color
            ),
        )
    }


    fun showHideFilter() {
        val t: Boolean = filterIsShown.value ?: false

        filterIsShown.value = !t
    }

    fun hideFilter() {
        filterIsShown.value = false
    }


    // selecting button, that had been touched
    fun selectCategory(selectedCategory: Int) {
        val list = getUnselectedList()
        when (selectedCategory) {
            Constants.PHONES -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.select_category_buttons_selected_bg,
                    icon = R.drawable.ic_svg_smartphone_light,
                    textColor = R.color.primary_color
                )
            }
            Constants.COMPUTERS -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.select_category_buttons_selected_bg,
                    icon = R.drawable.ic_svg_computer_light,
                    textColor = R.color.primary_color
                )
            }
            Constants.HEALTH -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.select_category_buttons_selected_bg,
                    icon = R.drawable.ic_svg_health_light,
                    textColor = R.color.primary_color
                )
            }
            Constants.BOOKS -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.select_category_buttons_selected_bg,
                    icon = R.drawable.ic_svg_books_light,
                    textColor = R.color.primary_color
                )
            }
            Constants.PHONES2 -> {
                list[selectedCategory - 1] = list[selectedCategory - 1].copy(
                    backGround = R.drawable.select_category_buttons_selected_bg,
                    icon = R.drawable.ic_svg_smartphone_light,
                    textColor = R.color.primary_color
                )
            }
        }
        buttonCategoriesState.value = list
    }

    private fun loadHotSales() {
        isHotSalesLoading.value = true
        viewModelScope.launch {
            val result = hotSalesUseCase.execute()
            hotSales.postValue(result)
            isHotSalesLoading.postValue(false)
        }
    }

    private fun loadBestSeller() {
        isBestSellerLoading.value = true
        viewModelScope.launch {
            val result = bestSellerUseCase.execute()
            bestSeller.postValue(result)
            isBestSellerLoading.postValue(false)
            allBestSellerItems = result
        }
    }
}
