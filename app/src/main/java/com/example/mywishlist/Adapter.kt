package com.example.mywishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    // ViewHolder describes an item view and metadata about its place within the RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTv)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPriceTv)
        val itemUrlTextView: TextView = itemView.findViewById(R.id.itemUrlTv)
    }

    // Creates new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val wishlistItemView = inflater.inflate(R.layout.wish, parent, false)
        // Return a new holder instance
        return ViewHolder(wishlistItemView)
    }

    // Replaces the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item: Item = items[position]

        // Set item views based on your views and data model
        holder.itemNameTextView.text = item.name
        holder.itemPriceTextView.text = item.price
        holder.itemUrlTextView.text = item.url
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return items.size
    }
}