package com.ord.coronastats.ui.country

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.data.repository.CountryStatsRepository

class CountryStatsViewModel(
    private val countryStatsRepository: CountryStatsRepository
) : ViewModel() {

    var countryStats = MutableLiveData<CountryStats>()

    fun fetchCountryStats(country: String) {
        countryStats = countryStatsRepository.fetchCountryStats(country)
    }


    fun select(item: CountryStats) {
        countryStats.value = item
    }

}
