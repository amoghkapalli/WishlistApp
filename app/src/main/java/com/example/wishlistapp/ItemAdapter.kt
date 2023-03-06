package com.example.wishlistapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (private val items: MutableList<Item>, private val context: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnLongClickListener,View.OnClickListener{
        var nameTextView: TextView
        val priceTextView: TextView
        val linkTextView: TextView

        init{
            nameTextView=itemView.findViewById(R.id.displayName)
            priceTextView=itemView.findViewById(R.id.displayPrice)
            linkTextView=itemView.findViewById(R.id.displayUrl)
            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener(this)
        }

        override fun onLongClick(p0: View?): Boolean {
            val curr=adapterPosition
            longClicker(curr)
            notifyItemRemoved(curr)
            return true
        }

        override fun onClick(p0: View?){
            val curr=adapterPosition
            shortClicker(curr)

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

    fun longClicker(position: Int){
        Toast.makeText(context, "longClick", Toast.LENGTH_LONG).show()
        items.removeAt(position)
        notifyDataSetChanged()
    }

    fun shortClicker(position: Int){
        val currentItem=items[position]
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.url.toString()))
            ContextCompat.startActivity(context, browserIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Invalid URL for " + currentItem.name, Toast.LENGTH_LONG).show()
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
}