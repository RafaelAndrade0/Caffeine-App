package com.example.coffeeappnavigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeappnavigation.R
import com.example.coffeeappnavigation.commom.MainActivity
import com.example.coffeeappnavigation.model.Coffee
import com.google.android.material.snackbar.Snackbar
import com.shashank.sony.fancytoastlib.FancyToast
import com.tapadoo.alerter.Alerter
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

        setRecyclerViewItemTouchListener(adapter)

        homeViewModel.allCoffee().observe(this, Observer { coffee ->
            recyclerView.smoothScrollToPosition(0)
            adapter.submitList(coffee)
        })

        val coffeeValue = pref?.getInt("caffeineValue", 0)

//        Toast.makeText(requireContext(), coffeeValue.toString(), Toast.LENGTH_SHORT).show()
        caffeine = coffeeValue ?: 0

        imageSmall.setOnClickListener {
            homeViewModel.saveCoffee(Coffee(caffeine = 10, coffeeSize = 0))
            showFancyToast(getString(R.string.small_coffee_description))
            caffeine += 10
            saveOnSharedPreferences(caffeine)
        }
        imageMedium.setOnClickListener {
            homeViewModel.deleteAll()
            saveOnSharedPreferences(0)
        }
        imageLarge.setOnClickListener {
            homeViewModel.saveCoffee(Coffee(caffeine = 30, coffeeSize = 2))
            showFancyToast(getString(R.string.large_coffee_description))
            caffeine += 30
            saveOnSharedPreferences(caffeine)
        }
    }

    private fun saveOnSharedPreferences(caffeineValue: Int) {
        val editor = pref?.edit()
        editor?.putInt("caffeineValue", caffeineValue)
        editor?.apply()
    }

    private fun setRecyclerViewItemTouchListener(adapter: HomeAdapter) {
        val itemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    viewHolder1: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val position = viewHolder.adapterPosition
                    homeViewModel.delete(adapter.getCoffeeAt(position))
                    undoAlerter()
//                    undoSnackbar()
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun saveCoffee(coffee: Coffee) {
        if (activity is MainActivity) {
            val listener = activity as OnButtonClick
            listener.onButtonClick(coffee)
        }
    }

    private fun undoAlerter() {
        Alerter.create(requireActivity())
            .setTitle("Item Removed!")
            .setDuration(4000)
            .setBackgroundColorRes(R.color.colorPrimary)
            .setText("Dou you want to revert it?")
            .enableSwipeToDismiss()
            .addButton("Yeah", R.style.AlertButton, View.OnClickListener {
                homeViewModel.undoDelete()
                Alerter.hide()
            })
            .addButton("No", R.style.AlertButton, View.OnClickListener {
                Alerter.hide()
            })
            .show()
    }

    private fun undoSnackbar() {
        Snackbar.make(requireView(), "Item Removido!", Snackbar.LENGTH_LONG)
            .setAction(
                "Reverter"
            ) {
                homeViewModel.undoDelete()
            }.show()
    }

    private fun showFancyToast(msg: String) {
        FancyToast.makeText(
            activity, "$msg added!", FancyToast.LENGTH_SHORT,
            FancyToast.SUCCESS, false
        ).show()
    }

    interface OnButtonClick {
        fun onButtonClick(coffee: Coffee)
    }
}