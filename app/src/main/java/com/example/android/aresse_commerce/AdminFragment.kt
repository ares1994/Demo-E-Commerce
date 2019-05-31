package com.example.android.aresse_commerce


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Database
import com.example.android.aresse_commerce.database.AppDatabase
import com.example.android.aresse_commerce.database.DatabaseProduct
import com.example.android.aresse_commerce.database.ProductDao
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_admin.view.*
import org.jetbrains.anko.doAsync

class AdminFragment : Fragment() {

    private lateinit var dataSource: ProductDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_admin, container, false)
        dataSource = AppDatabase.getInstance(container!!.context).productDatabaseDao




        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton.setOnClickListener {
            doAsync {
                dataSource.insertAll(DatabaseProduct(null, newProductEditText.text.toString(), 1.99))
            }
        }
    }
}
