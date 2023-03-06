package com.example.wishlistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val items: MutableList<Item>, private val context: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameTextView: TextView
        val priceTextView: TextView
        val linkTextView: TextView

        init{
            nameTextView=itemView.findViewById(R.id.displayName)
            priceTextView=itemView.findViewById(R.id.displayPrice)
            linkTextView=itemView.findViewById(R.id.displayUrl)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context=parent.context
        val inflater= LayoutInflater.from(context)
        val itemsList = inflater.inflate(R.layout.itemlayout, parent, false)
        // Return a new holder instance
        return ViewHolder(itemsList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=items[position]
        holder.nameTextView.text=currentItem.name
        holder.priceTextView.text=currentItem.price.toString()
        holder.linkTextView.text=currentItem.url.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}