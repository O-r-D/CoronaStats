package com.ord.coronastats.ui.countrylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.ord.coronastats.R
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var viewModel: CountryListViewModel

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

        viewModel.fetchCountriesWithStats().observe(viewLifecycleOwner, Observer {
            tv_test.text = it.toString()
        })
    }

}
