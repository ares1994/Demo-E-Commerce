package com.example.android.aresse_commerce


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.*

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.aresse_commerce.database.AppDatabase
import com.example.android.aresse_commerce.database.ProductDao
import com.example.android.aresse_commerce.model.Product
import com.example.android.aresse_commerce.repos.ProductsRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.product_row.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.uiThread
import java.net.URL


class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    @SuppressLint("CheckResult")
    override fun onQueryTextChange(newText: String?): Boolean {
        ProductsRepository().searchProducts(newText as String).subscribe({
            reloadRecyclerView(it)
        },{})
        return false
    }


    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ProductsRepository().getAllProducts().subscribe({
            reloadRecyclerView(it)
        }, { Log.d("Ares", "Eyah") })

        val categories =
            listOf("Socks", "Shoes", "Shirts", "Belts", "Jeans", "Suits", "Bags", "Perfumes", "Bonnets", "Pants")

        categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

//        searchButton.setOnClickListener {
//            if (searchEditText.text.toString().isEmpty()) {
//                return@setOnClickListener
//            }
//            ProductsRepository().searchProducts(searchEditText.text.toString().trim()).subscribe({
//                reloadRecyclerView(it)
//            }, {})
//        }

    }

    private fun reloadRecyclerView(it: List<Product>) {
        recycler_view.layoutManager = GridLayoutManager(activity, 2)
        recycler_view.adapter = ProductsAdapter(it) { title, productUrl, photoView ->

            val intent = Intent(activity, ProductDetails::class.java)
            intent.putExtra("name", title)
            intent.putExtra("productUrl", productUrl)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity as AppCompatActivity,
                photoView,
                "photoToAnimate"
            )
            startActivity(intent, options.toBundle())


        }
        progressBar.visibility = View.GONE
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_fragment,menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }


}


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

//val application = activity!!.applicationContext
//
//
//        doAsync {
//            dataSource = AppDatabase.getInstance(application).productDatabaseDao
////            dataSource.insertAll(DatabaseProduct(null, "Denim Jeans", 5.99))
//
//            val list = dataSource.getAll()
//
//            val products = list.map {
//                Product(it.title, "https://finepointmobile.com/data/jeans2.jpg", it.price, true)
//            }
//            runOnUiThread {
//
//                d(
//                    "Ares",
//                    "Size of list of products in database are ${list.size}, Also first item is: ${list[0].title} "
//                )
//                root.recycler_view.apply {
//                    layoutManager = GridLayoutManager(activity, 2)
//                    adapter = ProductsAdapter(products)
////                    root.progressBar.visibility = View.GONE
//                }
//                root.progressBar.visibility = View.GONE
//            }
//
//
//        }


//            doAsync {
//                val searchedList = dataSource.searchFor("%${searchEditText.text}%")
//
//                val products = searchedList.map {
//                    Product(it.title, "https://finepointmobile.com/data/jeans2.jpg", it.price, true)
//                }
//
//                runOnUiThread {
//                    recycler_view.adapter = ProductsAdapter(products)
//                }
//            }
//
//