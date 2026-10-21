
package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val averageText =
            view.findViewById<TextView>(R.id.averageCalories)

        val minimumText =
            view.findViewById<TextView>(R.id.minimumCalories)

        val maximumText =
            view.findViewById<TextView>(R.id.maximumCalories)

        val clearButton =
            view.findViewById<Button>(R.id.clearDataButton)

        val database =
            FoodDatabase.getDatabase(requireContext())

        // Observe food entries and update statistics
        viewLifecycleOwner.lifecycleScope.launch {
            database.foodDao().getAllFoods().collect { foods ->

                if (foods.isEmpty()) {
                    averageText.text = "0"
                    minimumText.text = "0"
                    maximumText.text = "0"
                } else {
                    val calories = foods.map { it.calories }

                    averageText.text =
                        calories.average().toInt().toString()

                    minimumText.text =
                        calories.minOrNull().toString()

                    maximumText.text =
                        calories.maxOrNull().toString()
                }
            }
        }

        clearButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {

                withContext(Dispatchers.IO) {
                    database.foodDao().deleteAllFoods()
                }

                Toast.makeText(
                    requireContext(),
                    "All food entries cleared",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
