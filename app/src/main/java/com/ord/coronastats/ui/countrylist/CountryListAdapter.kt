package com.ord.coronastats.ui.countrylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ord.coronastats.R
import com.ord.coronastats.data.model.CountryStats
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(
    private val countries: List<CountryStats>
) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry = itemView.tv_country_name
        val tvCases = itemView.tv_country_cases
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false).let {
            return CountryViewHolder(it)
        }
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.tvCountry.text = countries[position].country
        holder.tvCases.text = String.format("%,d", countries[position].cases)
    }
}