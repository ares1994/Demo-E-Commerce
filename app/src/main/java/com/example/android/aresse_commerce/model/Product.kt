package com.example.android.aresse_commerce.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val title: String,

    @SerializedName("photo_url")
    val productUrl: String,

    val price: Double,


    val isOnSale : Boolean
)