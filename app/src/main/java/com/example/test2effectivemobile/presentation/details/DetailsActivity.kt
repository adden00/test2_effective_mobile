package com.example.test2effectivemobile.presentation.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.test2effectivemobile.databinding.ActivityDetailsBinding
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