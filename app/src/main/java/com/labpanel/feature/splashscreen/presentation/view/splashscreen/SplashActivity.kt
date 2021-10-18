package com.labpanel.feature.splashscreen.presentation.view.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.home.presentation.view.dashboard.DashboardActivity
import com.labpanel.R
import com.labpanel.feature.splashscreen.presentation.view.viewevents.SplashState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        statusBarColors()
        subscribeSplashScreen()
    }

    private fun statusBarColors() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = getColor(R.color.white_ffffff)
    }

    private fun subscribeSplashScreen() {
        viewModel.onSplashScreenState.observe(this, {
            when(it) {
                is SplashState.OpenDashboard -> goToDashboardActivity()
            }
        })
    }

    private fun goToDashboardActivity() {
        finish()
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}
