package com.luis.coderswag.Controller

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.luis.coderswag.Adapters.ProductsAdapter
import com.luis.coderswag.R
import com.luis.coderswag.Services.DataService
import com.luis.coderswag.Utils.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryType: String = intent.getStringExtra(EXTRA_CATEGORY)
        adapter = ProductsAdapter(this, DataService.getProducts(categoryType))

        val orientation = resources.configuration.orientation
        val screenSize = resources.configuration.screenWidthDp

        var spanCount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) spanCount = 3
        if (screenSize > 720) spanCount = 3


        val layoutManager = GridLayoutManager(this, spanCount)
        productListView.layoutManager = layoutManager
        productListView.adapter = adapter
    }
}
