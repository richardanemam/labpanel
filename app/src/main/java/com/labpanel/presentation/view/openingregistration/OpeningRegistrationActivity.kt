package com.labpanel.presentation.view.openingregistration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.labpanel.R

class OpeningRegistrationActivity : AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_registration)

        initViews()
    }

    private fun initViews() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            title = getString(R.string.opening_registration_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}