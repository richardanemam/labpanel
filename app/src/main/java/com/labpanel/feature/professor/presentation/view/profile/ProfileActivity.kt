package com.labpanel.feature.professor.presentation.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.labpanel.R
import com.labpanel.feature.professor.domain.helper.UserAuthHelper
import com.labpanel.feature.app.domain.listener.DetailsListener
import com.labpanel.feature.app.domain.model.OpeningsDataModel
import com.labpanel.feature.app.presentation.view.adapter.OpeningsAdapter
import com.labpanel.feature.app.presentation.view.openinginfo.OpeningInfoActivity
import com.labpanel.feature.app.presentation.view.viewevents.LoadingState
import com.labpanel.feature.professor.domain.states.OpeningsState
import com.labpanel.feature.professor.presentation.view.openingregistration.OpeningRegistrationActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity(), DetailsListener {

    private val tvName by lazy { findViewById<TextView>(R.id.tv_profile_name) }
    private val tvEmail by lazy { findViewById<TextView>(R.id.tv_profile_email) }
    private val tvInitials by lazy { findViewById<TextView>(R.id.tv_profile_initials) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val registrationBtn by lazy { findViewById<FloatingActionButton>(R.id.fab_profile_opening_registration) }
    private val rvOpenings by lazy { findViewById<RecyclerView>(R.id.rv_profile_openings) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.pb_profile) }

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
        subscribeUI()
        viewModel.getOpenings()
    }

    private fun initViews() {
        setUpToolbar()
        setUpProfile()
        clickToRegister()

    }

    private fun subscribeUI() {
        subscribeOpenings()
        subscribeLoading()
    }

    private fun subscribeOpenings() {
        viewModel.onOpeningsState.observe(this, {
            when (it) {
                is OpeningsState.AvailableOpenings -> {
                    setUpOpeningsRecyclerView(it.data)
                }
                OpeningsState.UnavailableOpenings -> {
                    //TODO set a better message
                    Toast.makeText(this, "Openings unavailable", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun subscribeLoading() {
        viewModel.onLoadingState.observe(this, {
            when(it) {
                LoadingState.Show -> progressBar.visibility = View.VISIBLE
                LoadingState.Hide -> progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.profile_vagas)
    }

    private fun setUpProfile() {
        tvInitials.text = UserAuthHelper
            .getFirebaseAuth()
            .currentUser
            ?.displayName
            ?.let { viewModel.getInitials(it) }

        tvName.text = UserAuthHelper.getFirebaseAuth().currentUser?.displayName
        tvEmail.text = UserAuthHelper.getFirebaseAuth().currentUser?.email
    }

    private fun clickToRegister() {
        registrationBtn.setOnClickListener {
            val intent = Intent(this, OpeningRegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpOpeningsRecyclerView(openings: List<OpeningsDataModel>) {
        rvOpenings.layoutManager = LinearLayoutManager(this)
        rvOpenings.adapter = OpeningsAdapter(openings, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                Firebase.auth.signOut()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClickDetailsButton(openingInfo: OpeningsDataModel) {
        val intent = Intent(this, OpeningInfoActivity::class.java)
        intent.putExtra(OpeningInfoActivity.EXTRA_OPENING_INFO, openingInfo)
        startActivity(intent)
    }
}