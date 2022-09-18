package com.lucasdias.catanddogsearcher.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.android_core.navigator.Navigator
import com.lucasdias.catanddogsearcher.databinding.ActivitySplashBinding
import org.koin.android.ext.android.inject

private const val DELAY_TIME = 3000L

class SplashActivity : AppCompatActivity() {

    private val navigator by inject<Navigator>()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        startNextScreenWithDelay()
    }

    private fun startNextScreenWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigator.navigateToSearch(
                activity = this,
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        }, DELAY_TIME)
    }
}
