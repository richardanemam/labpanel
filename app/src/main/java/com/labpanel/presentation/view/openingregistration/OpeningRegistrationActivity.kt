package com.labpanel.presentation.view.openingregistration

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.labpanel.R
import com.labpanel.domain.auth.model.NewOpeningRegistrationModel

class OpeningRegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val degreesSp by lazy { findViewById<Spinner>(R.id.sp_opening_registration_courses) }
    private val edtTitle by lazy { findViewById<EditText>(R.id.edt_opening_registration_title) }
    private val edtDescription by lazy { findViewById<EditText>(R.id.edt_opening_registration_description) }
    private val edtActivities by lazy { findViewById<EditText>(R.id.edt_opening_registration_activities) }
    private val edtPrerequisite by lazy { findViewById<EditText>(R.id.edt_opening_registration_prerequisite) }
    private val edtEmail by lazy { findViewById<EditText>(R.id.edt_opening_registration_email) }
    private val btnRegister by lazy { findViewById<Button>(R.id.btn_opening_registration_register) }

    private val viewModel by lazy { ViewModelProviders.of(this)[OpeningRegistrationViewModel::class.java] }
    private val openingDataBase by lazy { FirebaseDatabase.getInstance() }
    private val openingDbReference by lazy { openingDataBase.getReference("RegisteredOpenings") }
    private lateinit var degree: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_registration)

        subscribeUI()
        initViews()
    }

    private fun subscribeUI() {
        subscribeDataValidation()
    }

    private fun initViews() {
        setUpToolbar()
        setDegreesSpinner()
        onRegisterButtonClick()
    }

    private fun subscribeDataValidation() {
        viewModel.onOpeningDataState.observe(this, Observer {
            when (it) {
                is OpeningDataState.ValidOpeningDataState -> {
                    addDataToFirebase(it.opening)
                }
                OpeningDataState.InvalidOpeningDataState -> {
                    handleEmptyTitleInput()
                    handleEmptyDescriptionInput()
                    handleEmptyActivitiesInput()
                    handleEmptyPrerequisitesInput()
                    handleEmptyEmailInput()
                }
            }
        })
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

    private fun onRegisterButtonClick() {
        btnRegister.setOnClickListener {
            viewModel.sendOpeningDataToDatabase(
                edtTitle.text.toString(), edtDescription.text.toString(),
                edtActivities.text.toString(), edtPrerequisite.text.toString(),
                edtEmail.text.toString(),
                degree
            )
        }
    }

    private fun addDataToFirebase(opening: NewOpeningRegistrationModel) {
        openingDbReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                openingDbReference.child("openings").child(opening.title).setValue(opening)
                Toast.makeText(this@OpeningRegistrationActivity, "Success", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@OpeningRegistrationActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun handleEmptyTitleInput() {
        if (TextUtils.isEmpty(edtTitle.text))
            edtTitle.error = getString(R.string.opening_registration_title_error)
    }

    private fun handleEmptyDescriptionInput() {
        if (TextUtils.isEmpty(edtDescription.text))
            edtDescription.error = getString(R.string.opening_registration_description_error)
    }

    private fun handleEmptyActivitiesInput() {
        if (TextUtils.isEmpty(edtActivities.text))
            edtActivities.error = getString(R.string.opening_registration_activities_error)
    }

    private fun handleEmptyPrerequisitesInput() {
        if (TextUtils.isEmpty(edtPrerequisite.text))
            edtPrerequisite.error = getString(R.string.opening_registration_prerequisite_error)
    }

    private fun handleEmptyEmailInput() {
        if (TextUtils.isEmpty(edtEmail.text) || !viewModel.validateEmail(edtEmail.text.toString()))
            edtEmail.error = getString(R.string.opening_registration_email_error)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        degree = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Does nothing
    }
}