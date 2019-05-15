package com.example.coffeeappnavigation.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coffeeappnavigation.model.Coffee
import com.example.coffeeappnavigation.model.Converters

@Database(entities = [Coffee::class], version = 2)
@TypeConverters(Converters::class)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeDao(): CoffeeDao

    companion object {
        private var instance: CoffeeDatabase? = null

        fun getDatabase(context: Context): CoffeeDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoffeeDatabase::class.java,
                    "dbCoffee"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as CoffeeDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}