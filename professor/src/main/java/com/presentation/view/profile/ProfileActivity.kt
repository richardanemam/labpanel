package com.presentation.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.domain.helper.UserAuthHelper
import com.openinginfo.domain.model.Openings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.openinginfo.domain.adapter.OpeningsAdapter
import com.openinginfo.domain.listener.DetailsListener
import com.openinginfo.presentation.mappers.OpeningDataMapper
import com.openinginfo.presentation.view.OpeningInfoActivity
import com.presentation.states.LoadingState
import com.presentation.states.ProfessorOpeningsState
import com.presentation.view.openingregistration.OpeningRegistrationActivity
import com.professor.R
import com.professor.databinding.ActivityProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity(), DetailsListener {

    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
        viewModel.onProfessorOpeningsState.observe(this, {
            when(it) {
               is ProfessorOpeningsState.AvailableOpenings -> {
                   setUpOpeningsRecyclerView(it.professorOpenings)
               }
                ProfessorOpeningsState.UnavailableOpenings -> {
                    //TODO set a better message
                    Toast.makeText(this, "Openings unavailable", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun subscribeLoading() {
        viewModel.onLoadingState.observe(this, {
            when (it) {
                LoadingState.Show -> binding.pbProfile.visibility = View.VISIBLE
                LoadingState.Hide -> binding.pbProfile.visibility = View.INVISIBLE
            }
        })
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbarId)
        supportActionBar?.title = getString(R.string.profile_vagas)
    }

    private fun setUpProfile() {
        binding.tvProfileInitials.text = UserAuthHelper
            .getFirebaseAuth()
            .currentUser
            ?.displayName
            ?.let { viewModel.getInitials(it) }

        binding.tvProfileName.text = UserAuthHelper.getFirebaseAuth().currentUser?.displayName
        binding.tvProfileEmail.text = UserAuthHelper.getFirebaseAuth().currentUser?.email
    }

    private fun clickToRegister() {
        binding.fabProfileOpeningRegistration.setOnClickListener {
            val intent = Intent(this, OpeningRegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpOpeningsRecyclerView(openings: List<Openings>) {
        binding.rvProfileOpenings.layoutManager = LinearLayoutManager(this)
        binding.rvProfileOpenings.adapter = OpeningsAdapter(openings, this)
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

    override fun onClickDetailsButton(openingInfo: Openings) {
        val intent = Intent(this, OpeningInfoActivity::class.java)
        intent.putExtra(OpeningInfoActivity.EXTRA_OPENING_INFO, OpeningDataMapper.map(openingInfo))
        startActivity(intent)
    }
}