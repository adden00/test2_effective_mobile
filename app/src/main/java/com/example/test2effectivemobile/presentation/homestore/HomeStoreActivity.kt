package com.example.test2effectivemobile.presentation.homestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2effectivemobile.R
import com.example.test2effectivemobile.databinding.ActivityHomeStoreBinding



class HomeStoreActivity : AppCompatActivity() {

    private val viewModel: HomeStoreViewModel by viewModels()
    private lateinit var binding: ActivityHomeStoreBinding
    private lateinit var adapter: ButtonsCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpinnerStyle()
        initRcAdapter()
        observeCategory()
//        fullscreen()
    }

    private fun fullscreen() {
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setSpinnerStyle() {
        binding.toolbarIncluded.spinnerLocation.adapter = ArrayAdapter.createFromResource (this, R.array.spinner_location_values, R.layout.spinner_text_style)
    }

    private fun initRcAdapter() {
        binding.rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ButtonsCategoryAdapter(object : ButtonsCategoryAdapter.Listener {
            override fun onClick(id: Int) {
                viewModel.selectCategory(id)
            }
        })
        binding.rcView.adapter = adapter
    }


    private fun observeCategory() {
        viewModel.buttonCategoriesState.observe(this){
            adapter.submitList(it)
        }
    }
}