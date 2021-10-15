package com.openinginfo.domain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openinginfo.domain.listener.DetailsListener
import com.example.libraries.R

class OpeningsAdapter(
    private val openingsList: List<OpeningsDataModel>,
    private val listener: DetailsListener
) : RecyclerView.Adapter<OpeningsAdapter.OpeningViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpeningViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_opening, parent, false)
        return OpeningViewHolder(itemView = itemView)
    }

    override fun getItemCount() = openingsList.size

    override fun onBindViewHolder(holder: OpeningViewHolder, position: Int) {
        holder.bindView()
    }

    inner class OpeningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val openingTitle: TextView = itemView.findViewById(R.id.tv_item_openings_title)
        private val openingDescription: TextView = itemView.findViewById(R.id.tv_item_openings_description)
        private val openingDetailsBtn: Button = itemView.findViewById(R.id.btn_opening_registration_register)

        fun bindView() {
            openingTitle.text = openingsList[absoluteAdapterPosition].title
            openingDescription.text = openingsList[absoluteAdapterPosition].description
            onClickOpeningDetailsButton()

        }

        private fun onClickOpeningDetailsButton() {
            openingDetailsBtn.setOnClickListener {
                listener.onClickDetailsButton(openingsList[absoluteAdapterPosition])
            }
        }
    }
}