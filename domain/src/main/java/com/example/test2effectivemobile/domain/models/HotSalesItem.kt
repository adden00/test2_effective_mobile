package com.example.test2effectivemobile.domain.models

data class HotSalesItem(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)