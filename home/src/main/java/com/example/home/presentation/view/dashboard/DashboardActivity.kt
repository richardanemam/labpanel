package com.example.home.presentation.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.home.R
import com.labpanel.feature.professor.presentation.view.auth.login.ProfessorLoginActivity
import com.labpanel.feature.student.presentation.view.allopenings.AllOpeningsActivity

class  DashboardActivity: AppCompatActivity() {

    private lateinit var btnProfessor: Button
    private lateinit var btnAluno: Button
    private lateinit var btnSobreApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initViews()
    }

    private fun initViews() {
        btnProfessor = findViewById(R.id.btn_dashboard_professor)
        btnAluno = findViewById(R.id.btn_dashboard_aluno)
        btnSobreApp = findViewById(R.id.btn_login_register)

        initProfessorButton()
        initAlunoButton()
        initSobreAppButton()
    }

    private fun initProfessorButton() {
        btnProfessor.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ProfessorLoginActivity::class.java))
        }
    }

    private fun initAlunoButton() {
        btnAluno.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, AllOpeningsActivity::class.java))
        }
    }

    private fun initSobreAppButton() {
        btnSobreApp.setOnClickListener {
            //TODO integrate with about the app screen
            Toast.makeText(this@DashboardActivity, "TODO integrate with about the app screen", Toast.LENGTH_LONG).show()
        }
    }
}