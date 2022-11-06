package com.example.test2effectivemobile.domain.models

data class HomeStoreItem(
    val best_seller: List<BestSellerItem>,
    val home_store: List<HotSalesItem>
)