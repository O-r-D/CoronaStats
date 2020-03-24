package com.ord.coronastats.ui.countrylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ord.coronastats.R
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var viewModel: CountryListViewModel
    private lateinit var rvCountries: RecyclerView
    private var countries = mutableListOf<CountryStats>()
    private var adapter = CountryListAdapter(countries)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideCountryListViewModelFactory().let {
            ViewModelProvider(this, it).get(CountryListViewModel::class.java)
        }

        activity?.also {
            rvCountries = it.rv_countries
        }

        viewModel.fetchCountriesWithStats().observe(viewLifecycleOwner, Observer {
            countries.clear()
            countries.addAll(it)
            adapter.notifyDataSetChanged()
        })

        rvCountries.layoutManager = LinearLayoutManager(context)
        rvCountries.adapter = adapter

    }

}
