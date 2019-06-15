package com.example.android.aresse_commerce

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.aresse_commerce.database.AppDatabase
import com.example.android.aresse_commerce.database.DatabaseProduct
import com.example.android.aresse_commerce.model.Product
import com.google.android.material.navigation.NavigationView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import java.net.URL


class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val application = requireNotNull(this).application


        Observable.create<String> {
            val json = URL("https://www.finepointmobile.com/data/products.json").readText()
            it.onNext(json)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Ares", it)
            }, { Log.d("Ares", "Error: Text not found") })


        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()


        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
//            drawer_layout.closeDrawer(GravityCompat.START)
            drawer_layout.closeDrawers()

            when (it.itemId) {
                R.id.action_settings -> Snackbar.make(drawer_layout, "Search Clicked", Snackbar.LENGTH_LONG).show()
                R.id.action_admin -> supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout,
                    AdminFragment()
                ).commit()

                R.id.action_home -> supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout,
                    MainFragment()
                ).commit()


            }

            return@setNavigationItemSelectedListener true
        }


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_cart) {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            return true
        }

        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}