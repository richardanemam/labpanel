package com.labpanel.presentation.view.auth.login

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.labpanel.databinding.ActivityProfessorLoginBinding
import com.labpanel.presentation.view.viewevents.LoadingState

class ProfessorLoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfessorLoginBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this)[ProfessorLoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        subscribeUI()
        initViews()

    }

    private fun subscribeUI() {
        subscribeLoadingEvent()
    }

    private fun initViews() {
        clickToLogin()
    }

    private fun subscribeLoadingEvent() {
        viewModel.onLoadingState.observe(this, Observer {
            when(it) {
                LoadingState.Show -> binding.progressBar.visibility = VISIBLE
                LoadingState.Hide -> binding.progressBar.visibility = INVISIBLE
            }
        })
    }

    private fun clickToLogin() {
        binding.btnAuthenticationContinue.setOnClickListener {
            viewModel.showLoading()
        }
    }
}