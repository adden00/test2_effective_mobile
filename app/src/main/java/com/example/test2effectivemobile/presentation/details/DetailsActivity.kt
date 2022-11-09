package com.example.test2effectivemobile.presentation.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ActivityDetailsBinding
import com.example.test2effectivemobile.databinding.ProductDetailsLayoutBinding
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
        setInfoCard()
        setButtons()




    }

    private fun setButtons() {
        binding.includeToolbar.button.setOnClickListener {
            finish()
        }
    }

    private fun setInfoCard() {
        LayoutInflater.from(this).inflate(R.layout.product_details_layout, binding.productDetailsHolder)
        val cardBinding = ProductDetailsLayoutBinding.bind(findViewById(R.id.detailsCardView))

        viewModel.productDetails.observe(this) {
            if (it != null) {
                cardBinding.tvName.text = it.title
                cardBinding.btnIsLiked.visibility = if (it.isFavorites) View.VISIBLE else View.GONE
                cardBinding.ratingBar.rating = it.rating.toFloat()
                cardBinding.tvProcessor.text = it.CPU
                cardBinding.tvCamera.text = it.camera
                cardBinding.tvRam.text = it.ssd
                cardBinding.tvMemory.text = it.sd
                cardBinding.tvPrice.text = "\$${it.price}"

            }
        }



    }



    private fun initImagesViewPager() = with (binding) {
        adapter = PhoneImagesAdapter()
        viewPagerPhoneImages.adapter = adapter
        viewPagerPhoneImages.clipToPadding = false
        viewPagerPhoneImages.clipChildren = false
        viewPagerPhoneImages.offscreenPageLimit = 3
        viewPagerPhoneImages.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val  r = 1 - abs(position)
            page.scaleY = 0.85f + r*0.15f
        }
        viewPagerPhoneImages.setPageTransformer(compositePageTransformer)

    }


    private fun observePhoneImages() {
        viewModel.imageUrlList.observe(this){
            Log.d("MyLog", it.toString())
            adapter.submitList(it)

        }
        viewModel.isImageLoading.observe(this){
            binding.pbarIsImageLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}