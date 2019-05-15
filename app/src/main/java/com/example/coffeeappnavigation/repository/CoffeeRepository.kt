package com.example.coffeeappnavigation.repository

import androidx.lifecycle.LiveData
import com.example.coffeeappnavigation.model.Coffee

interface CoffeeRepository {
    fun save(coffee: Coffee)
    fun update(coffee: Coffee)
    fun delete(vararg nCoffee: Coffee)
    fun allCoffes(): LiveData<List<Coffee>>
    fun deleteAll()
}