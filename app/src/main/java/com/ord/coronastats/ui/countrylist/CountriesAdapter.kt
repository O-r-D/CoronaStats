package com.ord.coronastats.ui.countrylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ord.coronastats.R
import com.ord.coronastats.data.model.CountryStats
import kotlinx.android.synthetic.main.item_country.view.*

class CountriesAdapter(
    private val countries: List<CountryStats>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false).let {
            return CountryViewHolder(it)
        }
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], itemClickListener)
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(countryStats: CountryStats, itemClickListener: OnItemClickListener) {
            itemView.tv_country_name.text = countryStats.country
            itemView.tv_country_cases.text = String.format("%,d", countryStats.cases)
            itemView.setOnClickListener{
                itemClickListener.setOnItemClickListener(countryStats)
            }
        }
    }

    interface OnItemClickListener{

        fun setOnItemClickListener(countryStats: CountryStats)
    }

}