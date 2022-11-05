package com.example.test2effectivemobile.presentation.homestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.presentation.homestore.model.ButtonCategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeStoreViewModel @Inject constructor() : ViewModel() {

    //    val currentCategory = MutableLiveData<Int>()
    val buttonCategoriesState = MutableLiveData<List<ButtonCategoryModel>>()
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
}