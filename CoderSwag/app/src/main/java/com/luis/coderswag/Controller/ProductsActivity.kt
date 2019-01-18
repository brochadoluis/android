package com.luis.coderswag.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luis.coderswag.R
import com.luis.coderswag.Utils.EXTRA_CATEGORY

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryType: String = intent.getStringExtra(EXTRA_CATEGORY)
        println(categoryType)
    }
}
