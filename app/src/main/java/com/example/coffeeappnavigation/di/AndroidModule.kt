package com.example.coffeeappnavigation.di

import com.example.coffeeappnavigation.home.HomeViewModel
import com.example.coffeeappnavigation.repository.CoffeeDatabase
import com.example.coffeeappnavigation.repository.CoffeeRepository
import com.example.coffeeappnavigation.repository.RoomRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val androidModule = module {
    single { this }
    single {
        RoomRepository(CoffeeDatabase.getDatabase(context = get())) as CoffeeRepository
    }
    viewModel {
        HomeViewModel(repository = get())
    }
}