package com.example.coffeeappnavigation.commom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.coffeeappnavigation.R
import com.example.coffeeappnavigation.home.HomeFragment
import com.example.coffeeappnavigation.home.HomeViewModel
import com.example.coffeeappnavigation.model.Coffee
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), HomeFragment.OnButtonClick {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.navHostFragment)
    }
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, navController)

        if (savedInstanceState == null) {
            setupBottomNavMenu(navController)
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onButtonClick(coffee: Coffee) {
        try {
            homeViewModel.saveCoffee(coffee)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
