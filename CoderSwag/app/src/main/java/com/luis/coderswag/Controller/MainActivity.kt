package com.luis.coderswag.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.luis.coderswag.Adapters.CategoryRecycleAdapter
import com.luis.coderswag.R
import com.luis.coderswag.Services.DataService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter : CategoryRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CategoryRecycleAdapter(this, DataService.categories)
        categoryListView.adapter = adapter

        val  layoutManager = LinearLayoutManager(this)
        categoryListView.layoutManager = layoutManager
        /* If cells do not change sizes, i.e. not dynamically changing
        * this ca be done for optimization on recyclerView*/
        categoryListView.setHasFixedSize(true)


//        does not work with recyclerViews
//        categoryListView.setOnItemClickListener { adapterView, view, i, l ->
//            val category = DataService.categories[i]
//            Toast.makeText(this, "You clicked on ${category.title} cell", Toast.LENGTH_SHORT).show()
//        }
    }
}
