package com.labpanel.presentation.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.labpanel.R
import com.labpanel.presentation.view.openingregistration.OpeningRegistrationActivity

class ProfileActivity: AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val registrationBtn by lazy { findViewById<FloatingActionButton>(R.id.fab_profile_opening_registration) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    private fun initViews() {
        setUpToolbar()
        clickToRegister()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.profile_vagas)
    }

    private fun clickToRegister() {
        registrationBtn.setOnClickListener {
            val intent = Intent(this, OpeningRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}