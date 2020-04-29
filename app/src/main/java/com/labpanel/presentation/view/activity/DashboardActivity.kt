package com.labpanel.presentation.view.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.labpanel.R

class DashboardActivity: AppCompatActivity() {

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
        btnSobreApp = findViewById(R.id.btn_dashboard_sobre_app)

        initProfessorButton()
        initAlunoButton()
        initSobreAppButton()
    }

    private fun initProfessorButton() {
        btnProfessor.setOnClickListener {
            //TODO integrate with loggin screen
            Toast.makeText(this@DashboardActivity, "TODO integrate with loggin screen", Toast.LENGTH_LONG).show()
        }
    }

    private fun initAlunoButton() {
        btnAluno.setOnClickListener {
            //TODO integrate with opportunities screen
            Toast.makeText(this@DashboardActivity, "TODO integrate with opportunities screen", Toast.LENGTH_LONG).show()
        }
    }

    private fun initSobreAppButton() {
        btnSobreApp.setOnClickListener {
            //TODO integrate with about the app screen
            Toast.makeText(this@DashboardActivity, "TODO integrate with about the app screen", Toast.LENGTH_LONG).show()
        }
    }
}