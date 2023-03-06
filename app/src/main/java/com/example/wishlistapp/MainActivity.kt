package com.example.wishlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){

    var wishlists : MutableList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishlistRecyclerView=findViewById<RecyclerView>(R.id.rvWishList)
        val adapter=ItemAdapter(wishlists, this)
        val submit=findViewById<Button>(R.id.submit)
        val url=findViewById<EditText>(R.id.itemUrl)
        val name=findViewById<EditText>(R.id.itemName)
        val price=findViewById<EditText>(R.id.itemPrice)

        submit.setOnClickListener{
            wishlists.add(Item(name.text.toString(), price.text.toString().toFloat(), url.text.toString().toUri()))
            adapter.notifyDataSetChanged()
        }
        wishlistRecyclerView.adapter = adapter
        wishlistRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}