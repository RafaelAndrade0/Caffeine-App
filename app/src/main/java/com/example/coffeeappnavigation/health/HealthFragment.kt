package com.example.coffeeappnavigation.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeeappnavigation.R
import kotlinx.android.synthetic.main.fragment_health.*

class HealthFragment : Fragment() {

    private val pref by lazy {
        activity?.getSharedPreferences("data", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.run {
//            val fullName = getString("full_name")
//            val age = getInt("age")
//            txtMessage.text = "$fullName - $age"
//        }

        val caffeine = pref?.getInt("caffeineValue", 0)
        textQuantity.text = getString(R.string.quantity_caffeine, caffeine)
        progressBar3.progress = caffeine ?: 0

    }
}