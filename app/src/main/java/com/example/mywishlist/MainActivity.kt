package com.example.mywishlist

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val items = mutableListOf<Item>()
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to all the views
        val itemNameEt = findViewById<EditText>(R.id.itemNameEt)
        val itemPriceEt = findViewById<EditText>(R.id.itemPriceEt)
        val itemUrlEt = findViewById<EditText>(R.id.itemUrlEt)
        val addButton = findViewById<Button>(R.id.addButton)
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)

        // Initialize the adapter and set it to the RecyclerView
        adapter = WishlistAdapter(items)
        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        // Add some test data to verify RecyclerView is working
        Log.d("MainActivity", "Initial setup complete. Adapter item count: ${adapter.itemCount}")

        // Set up the click listener for the 'Add' button
        addButton.setOnClickListener {
            // Get text from the EditText fields and trim whitespace
            val name = itemNameEt.text.toString().trim()
            val priceText = itemPriceEt.text.toString().trim()
            val url = itemUrlEt.text.toString().trim()

            Log.d("MainActivity", "Add button clicked. Name: '$name', Price: '$priceText', URL: '$url'")

            // Validate input
            when {
                name.isEmpty() -> {
                    itemNameEt.error = "Item name is required"
                    itemNameEt.requestFocus()
                    return@setOnClickListener
                }
                priceText.isEmpty() -> {
                    itemPriceEt.error = "Price is required"
                    itemPriceEt.requestFocus()
                    return@setOnClickListener
                }
                url.isEmpty() -> {
                    itemUrlEt.error = "URL is required"
                    itemUrlEt.requestFocus()
                    return@setOnClickListener
                }
            }

            // Validate and format price
            val price = try {
                val priceValue = priceText.toDouble()
                String.format("%.2f", priceValue)
            } catch (e: NumberFormatException) {
                itemPriceEt.error = "Please enter a valid price"
                itemPriceEt.requestFocus()
                return@setOnClickListener
            }

            // Create a new Item object
            val newItem = Item(name, price, url)
            Log.d("MainActivity", "Creating new item: $newItem")

            // Add the new item to the list
            items.add(newItem)
            Log.d("MainActivity", "Item added to list. List size: ${items.size}")

            // Notify the adapter that an item has been added
            adapter.notifyItemInserted(items.size - 1)
            Log.d("MainActivity", "Adapter notified. Adapter item count: ${adapter.itemCount}")

            // Scroll to the newly added item
            wishlistRv.scrollToPosition(items.size - 1)

            // Clear the input fields
            itemNameEt.text.clear()
            itemPriceEt.text.clear()
            itemUrlEt.text.clear()

            // Clear any existing errors
            itemNameEt.error = null
            itemPriceEt.error = null
            itemUrlEt.error = null

            // Show success message
            Toast.makeText(this, "Item added to wishlist!", Toast.LENGTH_SHORT).show()
        }
    }
}