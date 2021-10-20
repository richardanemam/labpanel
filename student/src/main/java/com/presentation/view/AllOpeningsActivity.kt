package com.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openinginfo.domain.adapter.OpeningsAdapter
import com.openinginfo.domain.listener.DetailsListener
import com.openinginfo.domain.model.OpeningVO
import com.openinginfo.domain.model.Openings
import com.openinginfo.presentation.mappers.OpeningDataMapper
import com.openinginfo.presentation.states.OpeningsState
import com.openinginfo.presentation.view.OpeningInfoActivity
import com.presentation.states.LoadingState
import com.student.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllOpeningsActivity: AppCompatActivity(), DetailsListener {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val rvAllOpenings by lazy { findViewById<RecyclerView>(R.id.rv_all_openings) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.pb_all_openings) }
    private val viewModel: AllOpeningsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_openings)

        initViews()
        subscribeUI()
        viewModel.fetchAllOpenings()
    }

    private fun initViews() {
        setUpToolbar()
    }

    private fun subscribeUI() {
        subscribeLoading()
        subscribeAllOpenings()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            title = getString(R.string.all_openings_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpOpeningsRecyclerView(openings: List<Openings>) {
        rvAllOpenings.layoutManager = LinearLayoutManager(this)
        rvAllOpenings.adapter = OpeningsAdapter(openings, this)
    }

    private fun subscribeLoading() {
        viewModel.onLoadingState.observe(this, {
            when(it) {
                LoadingState.Show -> progressBar.visibility = View.VISIBLE
                LoadingState.Hide -> progressBar.visibility = View.INVISIBLE
            }
        })
    }

    private fun  subscribeAllOpenings() {
        viewModel.onOpeningsState.observe(this, {
            when(it) {
                is OpeningsState.AvailableOpenings -> {
                    setUpOpeningsRecyclerView(it.openings)
                }
                OpeningsState.UnavailableOpenings -> {
                    Toast.makeText(this, "Openings unavailable", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClickDetailsButton(openingInfo: Openings) {
        val intent = Intent(this, OpeningInfoActivity::class.java)
        intent.putExtra(OpeningInfoActivity.EXTRA_OPENING_INFO, OpeningDataMapper.map(openingInfo))
        startActivity(intent)
    }
}