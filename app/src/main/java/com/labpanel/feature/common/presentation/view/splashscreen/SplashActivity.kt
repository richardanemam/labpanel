package com.labpanel.feature.common.presentation.view.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.labpanel.R
import com.labpanel.feature.common.presentation.view.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this)[SplashViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        statusBarColors()
        initViewModel()
    }

    private fun statusBarColors() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = getColor(R.color.white_ffffff)
    }

    private fun initViewModel() {
        viewModel.onSplashScreenState.observe(this, {
            when(it) {
                is SplashViewModel.SplashState.DashboardActivity -> goToDashboardActivity()
            }
        })
    }

    private fun goToDashboardActivity() {
        finish()
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}
