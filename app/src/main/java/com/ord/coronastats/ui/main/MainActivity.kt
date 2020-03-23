package com.ord.coronastats.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.R
import com.ord.coronastats.ui.country.CountryStatsFragment
import com.ord.coronastats.ui.countrylist.CountryListFragment
import com.ord.coronastats.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmenting(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            InjectorUtils.provideMainViewModelFactory()
        ).get(MainViewModel::class.java)

        viewModel.fetchWorldStats().observe(this, Observer {
            tv_total_cases_nb.text = String.format("%,d", it.cases)
            tv_dead_nb.text = String.format("%,d", it.deaths)
            tv_recovered_nb.text = String.format("%,d", it.recovered)
        })

    }

    private fun fragmenting(savedInstanceState: Bundle?) {
        if (fragment != null) {

            if (savedInstanceState != null)
                return

            supportFragmentManager.beginTransaction().add(R.id.fragment, CountryListFragment()).commit()
        }
    }
}
