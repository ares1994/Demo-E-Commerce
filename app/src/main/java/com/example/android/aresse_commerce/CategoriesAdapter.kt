package com.example.android.aresse_commerce

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val categories: List<String>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        val holder = ViewHolder(view)


        return holder

    }

    override fun getItemCount(): Int = categories.size


    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        holder.category.text = categories[position]
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.categoriesTextView)
    }
}



