package com.example.coffeeappnavigation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "coffee")
data class Coffee(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var addedAt: Date = Date(),
    var coffeeSize: Int = 0,
    var caffeine: Int = 0
) {
    override fun toString(): String {
        return super.toString()
    }
}