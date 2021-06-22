package com.labpanel.presentation.view.profile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.labpanel.R
import com.labpanel.presentation.view.openingregistration.OpeningRegistrationActivity

class ProfileActivity: AppCompatActivity() {

    private val tvName by lazy { findViewById<TextView>(R.id.tv_profile_name) }
    private val tvEmail by lazy { findViewById<TextView>(R.id.tv_profile_email) }
    private val tvInitials by lazy { findViewById<TextView>(R.id.tv_profile_initials) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val registrationBtn by lazy { findViewById<FloatingActionButton>(R.id.fab_profile_opening_registration) }

    private val viewModel by lazy { ViewModelProviders.of(this)[ProfileViewModel::class.java] }
    private val user by lazy { Firebase.auth.currentUser }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    private fun initViews() {
        setUpToolbar()
        setUpProfile()
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

    private fun setUpProfile() {
        tvInitials.text = user?.displayName?.let { viewModel.getInitials(it) }
        tvName.text = user?.displayName
        tvEmail.text = user?.email
    }
}