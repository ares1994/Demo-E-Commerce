package com.example.android.aresse_commerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.aresse_commerce.model.Product
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductsAdapter(private val products: List<Product>, private val onCLickProduct : ( title: String, productUrl: String, photoView: View)-> Unit) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.productUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = "\$${product.price}"
        if (product.isOnSale) {
            holder.isOnSaleImage.visibility = View.VISIBLE
        }

        holder.image.setOnClickListener {
//
            onCLickProduct.invoke(product.title, product.productUrl,holder.image)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val isOnSaleImage: ImageView = itemView.isOnSaleIcon


    }

}