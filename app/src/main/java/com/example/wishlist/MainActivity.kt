package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishlistRv = findViewById<RecyclerView>(R.id.wishlist_rv)
        val itemNameEt = findViewById<EditText>(R.id.item_name_et)
        val priceEt = findViewById<EditText>(R.id.price_et)
        val urlEt = findViewById<EditText>(R.id.url_et)
        val addItemBtn = findViewById<Button>(R.id.add_item_btn)

        wishlistAdapter = WishlistAdapter(wishlistItems)
        wishlistRv.adapter = wishlistAdapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        addItemBtn.setOnClickListener {
            val name = itemNameEt.text.toString()
            val price = priceEt.text.toString().toDoubleOrNull()
            val url = urlEt.text.toString()

            if (name.isNotEmpty() && price != null && url.isNotEmpty()) {
                val newItem = WishlistItem(name, price, url)
                wishlistItems.add(newItem)
                wishlistAdapter.notifyItemInserted(wishlistItems.size - 1)
                itemNameEt.text.clear()
                priceEt.text.clear()
                urlEt.text.clear()
            }
        }
    }
}