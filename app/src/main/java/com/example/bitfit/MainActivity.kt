
package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the RecyclerView and button
        val recyclerView = findViewById<RecyclerView>(
            R.id.foodRecyclerView
        )

        val addButton = findViewById<Button>(
            R.id.addFoodButton
        )

        // Create the adapter with an empty list
        foodAdapter = FoodAdapter(emptyList())

        // Set up the RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = foodAdapter

        // Get the Room database
        val database = FoodDatabase.getDatabase(this)

        // Observe saved foods and update the list
        lifecycleScope.launch {
            database.foodDao().getAllFoods().collect { foods ->
                foodAdapter.updateFoods(foods)
            }
        }

        // Open the Add Food screen
        addButton.setOnClickListener {
            val intent = Intent(
                this,
                AddFoodActivity::class.java
            )

            startActivity(intent)
        }
    }
}
