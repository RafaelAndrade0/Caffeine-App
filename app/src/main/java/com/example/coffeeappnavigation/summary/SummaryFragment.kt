package com.example.coffeeappnavigation.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coffeeappnavigation.R

class SummaryFragment : Fragment() {

    private val pref by lazy {
        activity?.getSharedPreferences("data", 0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val totalCaffeine = pref?.getInt("caffeineValue", 0)
        Toast.makeText(requireContext(), "Value: $totalCaffeine", Toast.LENGTH_SHORT).show()
    }
}