package com.example.bitfit
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        // Find the input fields and button
        val foodInput = findViewById<EditText>(
            R.id.foodEditText
        )

        val caloriesInput = findViewById<EditText>(
            R.id.caloriesEditText
        )

        val saveButton = findViewById<Button>(
            R.id.saveFoodButton
        )

        // Get the Room database
        val database = FoodDatabase.getDatabase(this)

        // Handle the save button click
        saveButton.setOnClickListener {

            // Get the user's input
            val foodName = foodInput.text.toString().trim()

            val calories = caloriesInput.text
                .toString()
                .toIntOrNull()

            // Check for valid input
            if (foodName.isEmpty() ||
                calories == null ||
                calories < 0) {

                Toast.makeText(
                    this,
                    "Please enter valid food information",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Create a new food entry
            val newFood = FoodEntity(
                name = foodName,
                calories = calories
            )

            // Save the food in the database
            lifecycleScope.launch {

                withContext(Dispatchers.IO) {
                    database.foodDao().insertFood(newFood)
                }

                // Return to the main screen
                finish()
            }
        }
    }
}
