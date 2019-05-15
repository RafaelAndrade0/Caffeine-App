package com.example.coffeeappnavigation.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappnavigation.R
import com.example.coffeeappnavigation.commom.MainActivity
import com.example.coffeeappnavigation.model.Coffee
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val pref by lazy {
        activity?.getSharedPreferences("data", 0)
    }
    private val homeViewModel by sharedViewModel<HomeViewModel>()
    private var caffeine: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter()
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        homeViewModel.allCoffee().observe(this, Observer { coffee ->
            recyclerView.smoothScrollToPosition(0)
            adapter.submitList(coffee)
        })

        val coffeeValue = pref?.getInt("caffeineValue", 0)

        Toast.makeText(requireContext(), coffeeValue.toString(), Toast.LENGTH_SHORT).show()
        caffeine = coffeeValue ?: 0

        imageSmall.setOnClickListener {
            val coffee = Coffee(caffeine = 10, coffeeSize = 0)
            homeViewModel.saveCoffee(coffee)
//            saveCoffee(coffee)
            caffeine += 10
            saveOnSharedPreferences(caffeine)
        }
        imageMedium.setOnClickListener {
            //            val coffee = Coffee(caffeine = 20, coffeeSize = 1)
//            saveCoffee(coffee)
            homeViewModel.deleteAll()
//            caffeine += 20
            saveOnSharedPreferences(0)
        }
        imageLarge.setOnClickListener {
            val coffee = Coffee(caffeine = 30, coffeeSize = 2)
            homeViewModel.saveCoffee(coffee)
//            saveCoffee(coffee)
            caffeine += 30
            saveOnSharedPreferences(caffeine)
        }
    }

    private fun saveOnSharedPreferences(caffeineValue: Int) {
        val editor = pref?.edit()
        editor?.putInt("caffeineValue", caffeineValue)
        editor?.apply()
    }

    private fun saveCoffee(coffee: Coffee) {
        if (activity is MainActivity) {
            val listener = activity as OnButtonClick
            listener.onButtonClick(coffee)
        }
    }

    interface OnButtonClick {
        fun onButtonClick(coffee: Coffee)
    }
}