package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert
    suspend fun insertFood(food: FoodEntity)

    @Query("SELECT * FROM food_table ORDER BY id DESC")
    fun getAllFoods(): Flow<List<FoodEntity>>
}