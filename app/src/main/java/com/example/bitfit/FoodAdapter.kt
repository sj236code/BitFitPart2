
package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private var foods: List<FoodEntity>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val foodName: TextView =
            view.findViewById(R.id.foodNameTextView)

        val calories: TextView =
            view.findViewById(R.id.caloriesTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val food = foods[position]

        holder.foodName.text = food.name
        holder.calories.text = food.calories.toString()
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun updateFoods(newFoods: List<FoodEntity>) {
        foods = newFoods
        notifyDataSetChanged()
    }
}
