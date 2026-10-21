package com.example.bitfit
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FoodLogFragment : Fragment() {

    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_food_log,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView =
            view.findViewById<RecyclerView>(
                R.id.foodRecyclerView
            )

        val addButton =
            view.findViewById<Button>(
                R.id.addFoodButton
            )

        foodAdapter = FoodAdapter(emptyList())

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerView.adapter = foodAdapter

        val database =
            FoodDatabase.getDatabase(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            database.foodDao().getAllFoods().collect { foods ->
                foodAdapter.updateFoods(foods)
            }
        }

        addButton.setOnClickListener {
            val intent = Intent(
                requireContext(),
                AddFoodActivity::class.java
            )

            startActivity(intent)
        }
    }
}
