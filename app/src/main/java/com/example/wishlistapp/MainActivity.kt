package com.example.wishlistapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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
        fun closeKeyboard() {
            val view = this.currentFocus
            if(view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
        val wishlistRecyclerView=findViewById<RecyclerView>(R.id.rvWishList)
        val adapter=ItemAdapter(wishlists, this)
        val submit=findViewById<Button>(R.id.submit)
        val url=findViewById<EditText>(R.id.itemUrl)
        val name=findViewById<EditText>(R.id.itemName)
        val price=findViewById<EditText>(R.id.itemPrice)

        submit.setOnClickListener{
            closeKeyboard()
            wishlists.add(Item(name.text.toString(), price.text.toString().toFloat(), url.text.toString().toUri()))
            url.text.clear()
            name.text.clear()
            price.text.clear()
            adapter.notifyDataSetChanged()
        }
        wishlistRecyclerView.adapter = adapter
        wishlistRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}