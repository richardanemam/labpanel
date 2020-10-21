package com.labpanel.presentation.view.auth.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.labpanel.databinding.ActivityProfessorRegistrationBinding

class ProfessorRegistrationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProfessorRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
    }

    private fun initViews() {
        loginInstead()
    }

    private fun loginInstead() {
        binding.btnRegistrationLoginInstead.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}