package com.example.coffeeappnavigation.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeappnavigation.model.Coffee
import com.example.coffeeappnavigation.repository.CoffeeRepository
import com.example.coffeeappnavigation.repository.RoomRepository

class HomeViewModel(
    private val repository: CoffeeRepository
) : ViewModel() {

    private val deletedItems = mutableListOf<Coffee>()

    fun saveCoffee(coffee: Coffee) {
        repository.save(coffee)
    }

    fun allCoffee(): LiveData<List<Coffee>> {
        return repository.allCoffes()
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun delete(vararg nCoffee: Coffee) {
        repository.delete(*nCoffee)
        deletedItems.clear()
        deletedItems.addAll(nCoffee)
    }

    fun undoDelete() {
        if (deletedItems.isNotEmpty()) {
            for (coffee in deletedItems) {
                repository.save(coffee)
            }
        }
    }
}