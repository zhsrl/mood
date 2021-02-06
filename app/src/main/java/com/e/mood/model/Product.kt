package com.e.mood.model

data class Product(
        var productImage: Int,
        var productName: String,
        var productDescription: String,
        var productSizes: List<String>
)