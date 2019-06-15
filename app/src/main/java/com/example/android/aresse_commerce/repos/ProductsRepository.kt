package com.example.android.aresse_commerce.repos

import com.example.android.aresse_commerce.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            val json = URL("https://www.finepointmobile.com/data/products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}