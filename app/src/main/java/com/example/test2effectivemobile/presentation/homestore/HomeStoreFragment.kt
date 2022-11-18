package com.example.test2effectivemobile.presentation.homestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.common.constants.Constants
import com.example.test2effectivemobile.databinding.FragmentHomeStoreBinding
import com.example.test2effectivemobile.presentation.homestore.adapters.BestSellerAdapter
import com.example.test2effectivemobile.presentation.homestore.adapters.ButtonsCategoryAdapter
import com.example.test2effectivemobile.presentation.homestore.adapters.HotSalesAdapter
import com.example.test2effectivemobile.presentation.homestore.models.FilterModel

class HomeStoreFragment : Fragment() {
    private val viewModel: HomeStoreViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeStoreBinding
    private lateinit var categoryAdapter: ButtonsCategoryAdapter
    private lateinit var hotSalesAdapter: HotSalesAdapter
    private lateinit var bestSellerAdapter: BestSellerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
        setSpinnersStyle()
        initRcAdapters()
        observeCategory()
        observeHotSales()
        observeBestSeller()
        observeFilterShowing()
        observeCartItemsCount()
        observeSwipeToRefresh()
    }

    private fun observeSwipeToRefresh() {
        viewModel.isBestSellerLoading.observe(requireActivity()) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadAllInfo()
        }
    }

    private fun observeCartItemsCount() {
        viewModel.cartItemsCount.observe(requireActivity()) {
            binding.includedBottomTapBar.tvCartItemsCount.visibility =
                if (it != 0) View.VISIBLE else View.GONE
            binding.includedBottomTapBar.tvCartItemsCount.text = it.toString()
        }
    }

    private fun setButtons() {
        binding.includedToolbar.btnFilter.setOnClickListener {
            viewModel.showHideFilter()
        }

        binding.includedFilter.btnClose.setOnClickListener {
            viewModel.filterBestSellerItems(
                FilterModel(
                    "All",
                    Constants.MIN_PRICE,
                    Constants.MAX_PRICE
                )
            )
            viewModel.hideFilter()
        }


        binding.includedFilter.btnDone.setOnClickListener {
            val brand = binding.includedFilter.spinnerBrand.selectedItem.toString()
            val price = binding.includedFilter.spinnerPrice.selectedItem.toString()
            val minPrice: Int
            val maxPrice: Int
            if (price == "All") {
                minPrice = Constants.MIN_PRICE
                maxPrice = Constants.MAX_PRICE
            } else {
                val priceRange = price.replace("\$", "").split("-").map { it.toInt() }
                minPrice = priceRange[0]
                maxPrice = priceRange[1]
            }
            viewModel.filterBestSellerItems(
                FilterModel(
                    brand = brand,
                    minPrice = minPrice,
                    maxPrice = maxPrice
                )
            )
        }

        binding.includedBottomTapBar.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeStoreFragment_to_cartFragment)
        }
    }


    private fun setSpinnersStyle() {
        val locationSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_location_values,
            R.layout.spinner_location_text_style
        )
        locationSpinnerAdapter.setDropDownViewResource(R.layout.spinner_location_text_style)
        binding.includedToolbar.spinnerLocation.adapter = locationSpinnerAdapter
        binding.includedToolbar.spinnerLocation.setPopupBackgroundResource(R.drawable.spinner_poup_bg)


        val brandSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_filter_brand_values,
            R.layout.spinner_filter_text_style
        )
        brandSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerBrand.adapter = brandSpinnerAdapter
        binding.includedFilter.spinnerBrand.setPopupBackgroundResource(R.drawable.spinner_poup_bg)

        val priceSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_filter_price_values,
            R.layout.spinner_filter_text_style
        )
        priceSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerPrice.adapter = priceSpinnerAdapter
        binding.includedFilter.spinnerPrice.setPopupBackgroundResource(R.drawable.spinner_poup_bg)

        val sizeSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_filter_size_values,
            R.layout.spinner_filter_text_style
        )
        sizeSpinnerAdapter.setDropDownViewResource(R.layout.spinner_filter_text_style)
        binding.includedFilter.spinnerSize.adapter = sizeSpinnerAdapter
        binding.includedFilter.spinnerSize.setPopupBackgroundResource(R.drawable.spinner_poup_bg)
    }

    private fun initRcAdapters() {
        binding.rcCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = ButtonsCategoryAdapter(object : ButtonsCategoryAdapter.Listener {
            override fun onClick(id: Int) {
                viewModel.selectCategory(id)
            }
        })
        binding.rcCategory.adapter = categoryAdapter
        binding.rcHotSales.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        hotSalesAdapter = HotSalesAdapter()
        binding.rcHotSales.adapter = hotSalesAdapter

        bestSellerAdapter = BestSellerAdapter(object : BestSellerAdapter.Listener {
            override fun onItemClick() {
                findNavController().navigate(R.id.action_homeStoreFragment_to_detailsFragment)
            }
        })
        binding.rcBestSeller.adapter = bestSellerAdapter

        binding.rcBestSeller.layoutManager = GridLayoutManager(requireContext(), 2)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rcCategory)
        snapHelper.attachToRecyclerView(binding.rcHotSales)
    }

    private fun observeCategory() {
        viewModel.buttonCategoriesState.observe(requireActivity()) {
            categoryAdapter.submitList(it)
        }
    }

    private fun observeHotSales() {
        viewModel.hotSales.observe(requireActivity()) {
            hotSalesAdapter.submitList(it)
        }
        viewModel.isHotSalesLoading.observe(requireActivity()) {
            binding.pbarHotSales.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun observeBestSeller() {
        viewModel.bestSeller.observe(requireActivity()) {
            bestSellerAdapter.setList(it)
        }
        viewModel.isBestSellerLoading.observe(requireActivity()) {
            binding.pbarBestSeller.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun observeFilterShowing() {
        viewModel.filterIsShown.observe(requireActivity()) {
            if (it) {
                binding.includedFilterLayout.visibility = View.VISIBLE
                binding.includedFilterLayout.startAnimation(
                    AnimationUtils.loadAnimation(
                        requireContext(),
                        R.anim.open_filter_anim
                    )
                )
                binding.includedToolbar.imgFilterToolbar.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_filter_touched
                    )
                )
            } else {
                val closeAnim =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.close_filter_anim)
                closeAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        binding.includedToolbar.imgFilterToolbar.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_filter
                            )
                        )
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        binding.includedFilterLayout.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(p0: Animation?) {}
                })
                binding.includedFilterLayout.startAnimation(closeAnim)

            }
        }
    }
}