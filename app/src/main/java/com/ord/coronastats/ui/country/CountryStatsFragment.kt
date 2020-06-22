package com.ord.coronastats.ui.country

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ord.coronastats.R
import com.ord.coronastats.ui.countrylist.CountriesFragment
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_country_stats.*

class CountryStatsFragment : Fragment() {

    companion object {
        fun newInstance() = CountryStatsFragment()
    }

    private lateinit var viewModel: CountryStatsViewModel
    private lateinit var country: String

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
            ViewModelProvider(requireActivity(), it).get(CountryStatsViewModel::class.java)
        }

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        country = (context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso ?: "CH"

        viewModel.fetchCountryStats(country)

        bindUI()


    }

    private fun bindUI() {

        viewModel.countryStats.observe(viewLifecycleOwner, Observer {
            tv_cases_nb.text = String.format("%,d", it.cases)

            tv_cases.text = getString(
                R.string.country_cases,
                if (it.todayCases != 0) "(${String.format("%,d", it.todayCases)})" else ""
            )

            tv_deaths_nb.text = String.format("%,d", it.deaths)

            tv_deaths.text = getString(
                R.string.country_dead,
                if (it.todayDeaths != 0) "(${String.format("%,d", it.todayDeaths)})" else ""
            )

            tv_recovered_nb.text = String.format("%,d", it.recovered)
            tv_recovered.text = getString(R.string.country_recovered)

//          tv_country_name.text = it.country

            toolbar.title = it.country
            toolbar.setTitleTextColor(Color.WHITE)
        })

        btn_change_country.setOnClickListener {
            goToCountryListFragment()
        }

        iv_refresh.setOnClickListener {
            activity?.recreate()
        }
    }

    private fun goToCountryListFragment() {
        CountriesFragment.newInstance().show(parentFragmentManager, "")
    }

}
