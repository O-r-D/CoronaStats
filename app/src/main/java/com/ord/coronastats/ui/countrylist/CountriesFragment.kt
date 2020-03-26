package com.ord.coronastats.ui.countrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ord.coronastats.R
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.ui.country.CountryStatsViewModel
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountriesFragment : DialogFragment(), CountriesAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    private lateinit var viewModel: CountriesViewModel
    private lateinit var countryStatsViewModel: CountryStatsViewModel
    private lateinit var rvCountries: RecyclerView
    private var countriesList = mutableListOf<CountryStats>()
    private var adapter = CountriesAdapter(countriesList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = InjectorUtils.provideCountryListViewModelFactory().let {
            ViewModelProvider(this, it).get(CountriesViewModel::class.java)
        }

        countryStatsViewModel = InjectorUtils.provideCountryStatsViewModelFactory().let {
            ViewModelProvider(activity!!, it).get(CountryStatsViewModel::class.java)
        }

        bindUI()


    }

    private fun bindUI() {

        rvCountries = rv_countries

        viewModel.apply {
            if (countries.value.isNullOrEmpty())
                fetchCountriesWithStats()
            countries.observe(viewLifecycleOwner, Observer {
                countriesList.clear()
                countriesList.addAll(it)
                adapter.notifyDataSetChanged()
            })
        }

        rvCountries.layoutManager = LinearLayoutManager(context)
        rvCountries.adapter = adapter
    }

    override fun setOnItemClickListener(countryStats: CountryStats) {
        countryStatsViewModel.countryStats.value = countryStats
        this.dismiss()
    }

}
