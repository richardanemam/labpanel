package com.example.labpanel.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.labpanel.R
import com.example.labpanel.presentation.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

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
        val viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.mutableLiveData.observe(this, Observer {
            when(it) {
                is SplashViewModel.SplashState.DashboardActivity -> goToDashboardActivity()
            }
        })
    }

    private fun goToDashboardActivity() {
       Toast.makeText(this, "SPLASH SCREEN FUNCIONANDO", Toast.LENGTH_LONG).show()
        finish()
    }
}
