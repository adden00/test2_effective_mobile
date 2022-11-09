package com.example.test2effectivemobile.domain.models

data class CartInfoItem(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)