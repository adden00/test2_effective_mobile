package com.example.test2effectivemobile.presentation.homestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ActivityHomeStoreBinding
import com.example.test2effectivemobile.presentation.homestore.adapters.BestSellerAdapter
import com.example.test2effectivemobile.presentation.homestore.adapters.ButtonsCategoryAdapter
import com.example.test2effectivemobile.presentation.homestore.adapters.HotSalesAdapter
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

        setButtons()
        setSpinnersStyle()
        initRcAdapters()
        observeCategory()
        observeHotSales()
        observeBestSeller()
        downloadInfo()


    }

    private fun setButtons() {
        binding.includedToolbar.btnFilter.setOnClickListener {
            viewModel.showHideFilter()
        }
        viewModel.filterIsShown.observe(this) {
            if (it) {
                binding.includedFilterLayout.visibility = View.VISIBLE
                binding.includedFilterLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.open_filter_anim))
                binding.includedToolbar.imgFilterToolbar.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_filter_touched))

            }
            else{
                val closeAnim = AnimationUtils.loadAnimation(this, R.anim.close_filter_anim)
                closeAnim.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        binding.includedToolbar.imgFilterToolbar.setImageDrawable(ContextCompat.getDrawable(this@HomeStoreActivity, R.drawable.ic_filter))
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        binding.includedFilterLayout.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(p0: Animation?) {}
                })
                binding.includedFilterLayout.startAnimation(closeAnim)

            }

        }

        binding.includedFilter.btnClose.setOnClickListener {
            bestSellerAdapter.filter.filter("")
            viewModel.hideFilter() }
        binding.includedFilter.btnDone.setOnClickListener {
            bestSellerAdapter.filter.filter(binding.includedFilter.spinnerBrand.selectedItem.toString())
            }
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

    private fun setSpinnersStyle() {
        val locationSpinnerAdapter =  ArrayAdapter.createFromResource (this, R.array.spinner_location_values, R.layout.spinner_location_text_style)
        locationSpinnerAdapter.setDropDownViewResource(R.layout.spinner_location_text_style)
        binding.includedToolbar.spinnerLocation.adapter = locationSpinnerAdapter
        binding.includedToolbar.spinnerLocation.setPopupBackgroundResource(R.drawable.spinner_poup_bg)


        val brandSpinnerAdapter = ArrayAdapter.createFromResource (this, R.array.spinner_filter_brand_values, R.layout.spinner_filter_text_style)
        brandSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerBrand.adapter = brandSpinnerAdapter
        binding.includedFilter.spinnerBrand.setPopupBackgroundResource(R.drawable.spinner_poup_bg)

        val priceSpinnerAdapter = ArrayAdapter.createFromResource (this, R.array.spinner_filter_price_values, R.layout.spinner_filter_text_style)
        priceSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerPrice.adapter = priceSpinnerAdapter
        binding.includedFilter.spinnerPrice.setPopupBackgroundResource(R.drawable.spinner_poup_bg)

        val sizeSpinnerAdapter = ArrayAdapter.createFromResource (this, R.array.spinner_filter_size_values, R.layout.spinner_filter_text_style)
        sizeSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerSize.adapter = sizeSpinnerAdapter
        binding.includedFilter.spinnerSize.setPopupBackgroundResource(R.drawable.spinner_poup_bg)



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
            bestSellerAdapter.setList(it)
        }
        viewModel.isBestSellerLoading.observe(this) {
            binding.pbarBestSeller.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}