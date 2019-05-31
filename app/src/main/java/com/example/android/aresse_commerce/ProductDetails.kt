package com.example.android.aresse_commerce

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        val intent = intent

        product_title.text = intent.getStringExtra("name")

        Picasso.get().load(intent.getStringExtra("productUrl")).into(photo)
        //Create the DialogInterface OnClickListener by passing as the second parameter object: DialogInterface.OnClickListener
        available_button.setOnClickListener {
            AlertDialog.Builder(this).setMessage("It\'s in stock").setPositiveButton(
                "OK"
            ) { p0, p1 -> }.create().show()
        }


    }
}
