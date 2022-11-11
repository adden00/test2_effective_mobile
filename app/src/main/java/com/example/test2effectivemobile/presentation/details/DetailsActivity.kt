package com.example.test2effectivemobile.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ActivityDetailsBinding
import com.example.test2effectivemobile.databinding.ProductDetailsCardBinding
import com.example.test2effectivemobile.domain.models.ProductDetailsItem
import com.example.test2effectivemobile.presentation.cart.CartActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var adapter: PhoneImagesAdapter
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initImagesViewPager()
        observePhoneImages()
        observeProductDetails()
        setButtons()
    }

    private fun observeProductDetails() {
        viewModel.productDetails.observe(this) {
            setInfoCard(binding.productDetailsHolder, it)
        }
    }

    private fun setButtons() {
        binding.includeToolbar.btnBack.setOnClickListener {
            finish()
        }
        binding.includeToolbar.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setInfoCard(parent: ViewGroup, item: ProductDetailsItem?) {
        LayoutInflater.from(parent.context).inflate(R.layout.product_details_card, parent)

        val cardBinding = ProductDetailsCardBinding.bind(findViewById(R.id.detailsCardView))

        if (item != null)  with(cardBinding) {
            tvName.text = item.title
            btnIsLiked.visibility = if (item.isFavorites) View.VISIBLE else View.GONE
            ratingBar.rating = item.rating.toFloat()
            tvProcessor.text = item.CPU
            tvCamera.text = item.camera
            tvRam.text = item.ssd
            tvMemory.text = item.sd
            tvPrice.text = "\$${item.price}"

            btnAddToCart.setOnClickListener {
                val snackBar = Snackbar.make(binding.root, "Added to cart", Snackbar.LENGTH_LONG)
                snackBar.setAction("Open cart") {
                    startActivity(Intent(this@DetailsActivity, CartActivity::class.java))
                }
                snackBar.show()
            }
        }


    }


    private fun initImagesViewPager() = with(binding) {
        adapter = PhoneImagesAdapter(binding.viewPagerPhoneImages)
        viewPagerPhoneImages.adapter = adapter
        viewPagerPhoneImages.clipToPadding = false
        viewPagerPhoneImages.clipChildren = false
        viewPagerPhoneImages.offscreenPageLimit = 3
        viewPagerPhoneImages.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPagerPhoneImages.setPageTransformer(compositePageTransformer)





    }


    private fun observePhoneImages() {
        viewModel.imageUrlList.observe(this) {
            Log.d("MyLog", it.toString())
            adapter.submitList(it)

        }
        viewModel.isImageLoading.observe(this) {
            binding.pbarIsImageLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}