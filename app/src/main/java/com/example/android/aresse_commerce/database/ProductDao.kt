package com.example.android.aresse_commerce.database

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {


    @Insert
    fun insertAll(vararg products: DatabaseProduct)

    @Query("SELECT * FROM products_table WHERE title LIKE :term ")
    fun searchFor(term:String) : List<DatabaseProduct>

    @Query("SELECT * FROM products_table")
    fun getAll(): List<DatabaseProduct>

}