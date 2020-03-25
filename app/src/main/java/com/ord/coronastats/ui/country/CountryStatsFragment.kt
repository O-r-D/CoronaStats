package com.ord.coronastats.ui.country

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.R
import com.ord.coronastats.ui.countrylist.CountryListFragment
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_country_stats.*

class CountryStatsFragment : Fragment() {

    companion object {
        fun newInstance() = CountryStatsFragment()
    }

    private lateinit var viewModel: CountryStatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_stats, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = InjectorUtils.provideCountryStatsViewModelFactory().let {
            ViewModelProvider(this, it).get(CountryStatsViewModel::class.java)
        }

        bindUI()

    }

    private fun bindUI() {

        val country = (context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso ?: "CH"

        viewModel.fetchCountryStats(country).observe(viewLifecycleOwner, Observer {
            tv_cases_nb.text = String.format("%,d", it.cases)
            if (it.todayCases != 0)
                tv_cases.text = getString(
                    R.string.country_cases,
                    "(${String.format("%,d", it.todayCases)})"
                )

            tv_deaths_nb.text = String.format("%,d", it.deaths)
            if (it.todayDeaths != 0)
                tv_deaths.text = getString(
                    R.string.country_dead,
                    "(${String.format("%,d", it.todayDeaths)})"
                )

            tv_recovered_nb.text = String.format("%,d", it.recovered)

            tv_country_name.text = it.country
        })

        btn_change_country.setOnClickListener {
            goToCountryListFragment()
        }
    }

    private fun goToCountryListFragment() {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, CountryListFragment())
            addToBackStack(null)
        }.commit()
    }

}
