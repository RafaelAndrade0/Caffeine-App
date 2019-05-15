package com.example.coffeeappnavigation.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coffeeappnavigation.model.Coffee

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coffee: Coffee): Long

    @Update
    fun update(coffee: Coffee): Int

    @Delete
    fun delete(vararg nCoffee: Coffee)

    @Query("SELECT * FROM coffee ORDER BY addedAt DESC")
    fun allCoffess(): LiveData<List<Coffee>>

    @Query("DELETE FROM coffee")
    fun deleteAll()
}