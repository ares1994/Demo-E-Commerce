package com.example.android.aresse_commerce.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class DatabaseProduct(

    @PrimaryKey(autoGenerate = true)
    val uid: Int? = 0,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val price: Double

)