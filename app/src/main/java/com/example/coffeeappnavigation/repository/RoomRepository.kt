package com.example.coffeeappnavigation.repository

import androidx.lifecycle.LiveData
import com.example.coffeeappnavigation.model.Coffee

class RoomRepository(database: CoffeeDatabase) : CoffeeRepository {

    private var coffeeDao: CoffeeDao = database.coffeDao()

    override fun save(coffee: Coffee) {
        coffeeDao.insert(coffee)
    }

    override fun update(coffee: Coffee) {
        coffeeDao.update(coffee)
    }

    override fun allCoffes(): LiveData<List<Coffee>> {
        return coffeeDao.allCoffess()
    }

    override fun deleteAll() {
        coffeeDao.deleteAll()
    }
}