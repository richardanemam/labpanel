package com.labpanel.presentation.view.openingregistration

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.labpanel.R

class OpeningRegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val degreesSp by lazy { findViewById<Spinner>(R.id.sp_opening_registration_courses) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_registration)

        initViews()
    }

    private fun initViews() {
        setUpToolbar()
        setDegreesSpinner()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            title = getString(R.string.opening_registration_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setDegreesSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.degrees_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            degreesSp.adapter = it
        }
        degreesSp.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, degreesSp.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Does nothing
    }
}