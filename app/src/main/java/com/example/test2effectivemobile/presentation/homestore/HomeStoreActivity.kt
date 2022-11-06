package com.example.test2effectivemobile.presentation.homestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ActivityHomeStoreBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeStoreActivity : AppCompatActivity() {

    private val viewModel: HomeStoreViewModel by viewModels()
    private lateinit var binding: ActivityHomeStoreBinding
    private lateinit var categoryAdapter: ButtonsCategoryAdapter
    private lateinit var hotSalesAdapter: HotSalesAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(findViewById(R.id.appBar))

        setSpinnerStyle()
        initRcAdapters()
        observeCategory()
        observeHotSales()
        observeBestSeller()
        downloadInfo()

    }



    private fun downloadInfo() {
        viewModel.loadHotSales()
        viewModel.loadBestSeller()
    }

//    private fun fullscreen() {
//        val w: Window = window
//        w.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
//    }

    private fun setSpinnerStyle() {
        binding.toolbarIncluded.spinnerLocation.adapter = ArrayAdapter.createFromResource (this, R.array.spinner_location_values, R.layout.spinner_text_style)
    }

    private fun initRcAdapters() {
//        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rcCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = ButtonsCategoryAdapter(object : ButtonsCategoryAdapter.Listener {
            override fun onClick(id: Int) {
                viewModel.selectCategory(id)
            }
        })
        binding.rcCategory.adapter = categoryAdapter

        binding.rcHotSales.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hotSalesAdapter = HotSalesAdapter()
        binding.rcHotSales.adapter = hotSalesAdapter

        bestSellerAdapter = BestSellerAdapter()
        binding.rcBestSeller.adapter = bestSellerAdapter
        binding.rcBestSeller.layoutManager = GridLayoutManager(this, 2)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcCategory)
        snapHelper.attachToRecyclerView(binding.rcHotSales)
    }


    private fun observeCategory() {
        viewModel.buttonCategoriesState.observe(this){
            categoryAdapter.submitList(it)
        }


    }

    private fun observeHotSales() {
        viewModel.hotSales.observe(this) {
            hotSalesAdapter.submitList(it)
        }
        viewModel.isHotSalesLoading.observe(this) {
            binding.pbarHotSales.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun observeBestSeller() {
        viewModel.bestSeller.observe(this) {
            bestSellerAdapter.submitList(it)
        }
        viewModel.isBestSellerLoading.observe(this) {
            binding.pbarBestSeller.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}