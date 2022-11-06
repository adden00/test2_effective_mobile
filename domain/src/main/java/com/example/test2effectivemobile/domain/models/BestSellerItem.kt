package com.example.test2effectivemobile.domain.models

data class BestSellerItem(
    val discount_price: Int,
    val id: Int,
    val is_favorites: Boolean,
    val picture: String,
    val price_without_discount: Int,
    val title: String
)