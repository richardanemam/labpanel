package com.labpanel.feature.student.presentation.view.allopenings

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.labpanel.R
import com.labpanel.feature.app.domain.listener.DetailsListener
import com.labpanel.feature.app.domain.model.OpeningsDataModel
import com.labpanel.feature.app.presentation.view.adapter.OpeningsAdapter
import com.labpanel.feature.app.presentation.view.viewevents.LoadingState
import com.labpanel.feature.professor.domain.states.OpeningsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllOpeningsActivity: AppCompatActivity(), DetailsListener {

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_id) }
    private val rvAllOpenings by lazy { findViewById<RecyclerView>(R.id.rv_all_openings) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.pb_all_openings) }
    private val viewModel: AllOpeningsViewModel by viewModel()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
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
        actionBar?.title = getString(R.string.all_openings_toolbar_title)
    }

    private fun setUpOpeningsRecyclerView(openings: List<OpeningsDataModel>) {
        rvAllOpenings.layoutManager = LinearLayoutManager(this)
        rvAllOpenings.adapter = OpeningsAdapter(openings, this)
        rvAllOpenings.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
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
                    setUpOpeningsRecyclerView(it.data)
                }
                OpeningsState.UnavailableOpenings -> {
                    Toast.makeText(this, "Openings unavailable", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClickDetailsButton(itemPosition: Int) {
        TODO("Not yet implemented")
    }
}