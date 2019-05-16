package com.example.coffeeappnavigation.health

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.coffeeappnavigation.R
import kotlinx.android.synthetic.main.fragment_health.*
import me.itangqi.waveloadingview.WaveLoadingView

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
        configureWaveLoading()
    }

    private fun calculateTotalPercentage(caffeine: Int) = (caffeine * 100) / 400

    private fun configureWaveLoading() {
        val totalCaffeine = pref?.getInt("caffeineValue", 0)
        val totalPercentage = calculateTotalPercentage(caffeine = totalCaffeine ?: 1)

        waveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE)
        waveLoadingView.topTitle = getString(R.string.msg_caffeine)
        waveLoadingView.centerTitle = getString(R.string.quantity_caffeine, totalCaffeine)
        waveLoadingView.bottomTitle = getString(R.string.porcentage_caffeine, totalPercentage)
        waveLoadingView.progressValue = totalPercentage
        waveLoadingView.borderWidth = 5.0F
        waveLoadingView.setAmplitudeRatio(60)
        waveLoadingView.waveColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        waveLoadingView.borderColor = Color.GRAY
        waveLoadingView.setAnimDuration(3000)
        waveLoadingView.pauseAnimation()
        waveLoadingView.resumeAnimation()
        waveLoadingView.cancelAnimation()
        waveLoadingView.startAnimation()
    }
}