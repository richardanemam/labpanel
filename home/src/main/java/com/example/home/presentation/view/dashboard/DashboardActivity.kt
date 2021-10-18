package com.example.home.presentation.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.home.databinding.ActivityDashboardBinding
import com.presentation.view.AllOpeningsActivity
import com.presentation.view.auth.login.ProfessorLoginActivity

class  DashboardActivity: AppCompatActivity() {

    private val binding by lazy { ActivityDashboardBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initProfessorButton()
        initAlunoButton()
        initSobreAppButton()
    }

    private fun initProfessorButton() {
        binding.btnDashboardProfessor.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ProfessorLoginActivity::class.java))
        }
    }

    private fun initAlunoButton() {
        binding.btnDashboardAluno.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, AllOpeningsActivity::class.java))
        }
    }

    private fun initSobreAppButton() {
        binding.btnAboutTheApp.setOnClickListener {
            //TODO integrate with about the app screen
            Toast.makeText(this@DashboardActivity, "TODO integrate with about the app screen", Toast.LENGTH_LONG).show()
        }
    }
}