package com.example.android.aresse_commerce


import android.os.Bundle
import android.util.Log.d

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.aresse_commerce.database.AppDatabase
import com.example.android.aresse_commerce.database.ProductDao
import com.example.android.aresse_commerce.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.uiThread
import java.net.URL


class MainFragment : Fragment() {

    private lateinit var dataSource: ProductDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        /*       doAsync {
                   val json = URL("https://www.finepointmobile.com/data/products.json").readText()

                   runOnUiThread {
                       val products = Gson().fromJson(json, Array<Product>::class.java).toList()
                       root.recycler_view.apply {
                           layoutManager = GridLayoutManager(activity, 2)
                           adapter = ProductsAdapter(products)
                           root.progressBar.visibility = View.GONE
                       }

                   }
               }           */

        val application = activity!!.applicationContext


        doAsync {
            dataSource = AppDatabase.getInstance(application).productDatabaseDao
//            dataSource.insertAll(DatabaseProduct(null, "Denim Jeans", 5.99))

            val list = dataSource.getAll()

            val products = list.map {
                Product(it.title, "https://finepointmobile.com/data/jeans2.jpg", it.price, true)
            }
            runOnUiThread {

                d(
                    "Ares",
                    "Size of list of products in database are ${list.size}, Also first item is: ${list[0].title} "
                )
                root.recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
//                    root.progressBar.visibility = View.GONE
                }
            }


        }

        root.progressBar.visibility = View.GONE

        val categories =
            listOf("Socks", "Shoes", "Shirts", "Belts", "Jeans", "Suits", "Bags", "Perfumes", "Bonnets", "Pants")

        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener {
            if (searchEditText.text.toString().isEmpty()) {
                return@setOnClickListener
            }
            doAsync {
                val searchedList = dataSource.searchFor("%${searchEditText.text}%")

                val products = searchedList.map {
                    Product(it.title, "https://finepointmobile.com/data/jeans2.jpg", it.price, true)
                }

                runOnUiThread {
                    recycler_view.adapter = ProductsAdapter(products)
                }
            }


        }
    }
}
