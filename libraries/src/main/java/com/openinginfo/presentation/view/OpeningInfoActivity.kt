package com.openinginfo.presentation.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.libraries.R
import com.openinginfo.domain.model.OpeningVO
import com.openinginfo.presentation.states.OnBundle

class OpeningInfoActivity: AppCompatActivity() {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val tvOpeningTitle by lazy { findViewById<TextView>(R.id.tv_opening_info_title_description) }
    private val tvOpeningDescription by lazy { findViewById<TextView>(R.id.tv_opening_info_description) }
    private val tvOpeningActivities by lazy { findViewById<TextView>(R.id.tv_opening_info_activities) }
    private val tvOpeningPrerequisite by lazy { findViewById<TextView>(R.id.tv_opening_info_prerequisite) }
    private val tvOpeningEmail by lazy { findViewById<TextView>(R.id.tv_opening_info_email) }
    private val tvOpeningDegrees by lazy { findViewById<TextView>(R.id.tv_opening_info_degrees) }

    private val viewModel by lazy { ViewModelProvider(this)[OpeningInfoViewModel::class.java]}
    private lateinit var openingInfo: OpeningVO

    companion object {
        const val EXTRA_OPENING_INFO = "openingInfo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_info)

        viewModel.validateExtras(intent)
        subscribeUI()
        initViews()
    }

    private fun initViews() {
        setUpToolbar()
    }

    private fun subscribeUI() {
        subscribeBundle()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            title = getString(R.string.opening_info_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeBundle() {
        viewModel.onBundleValidation.observe(this, {
            when(it) {
                OnBundle.BundleOk -> onBundleOk()
                OnBundle.BundleNok -> onBundleNok()
            }
        })
    }

    private fun onBundleOk() {
        initExtras()
        setViews()
    }

    private fun onBundleNok() {
        finish()
    }

    private fun initExtras() {
        openingInfo = intent.getParcelableExtra(EXTRA_OPENING_INFO) as OpeningVO
    }

    private fun setViews() {
        tvOpeningTitle.text = openingInfo.title
        tvOpeningDescription.text = openingInfo.description
        tvOpeningActivities.text = openingInfo.activities
        tvOpeningPrerequisite.text = openingInfo.prerequisites
        tvOpeningDegrees.text = openingInfo.degree
        tvOpeningEmail.text = openingInfo.email
    }
}