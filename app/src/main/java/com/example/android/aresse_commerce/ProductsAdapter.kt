package com.example.android.aresse_commerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.aresse_commerce.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("name", products[holder.adapterPosition].title)
            intent.putExtra("productUrl", products[holder.adapterPosition].productUrl)
            parent.context.startActivity(intent)
        }
        return holder
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
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val isOnSaleImage: ImageView = itemView.isOnSaleIcon


    }

}