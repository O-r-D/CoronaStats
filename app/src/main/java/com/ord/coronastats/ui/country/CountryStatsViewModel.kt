package com.ord.coronastats.ui.country

import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.repository.CountryStatsRepository

class CountryStatsViewModel(
    private val countryStatsRepository: CountryStatsRepository
) : ViewModel() {

    fun fetchCountryStats(country: String) = countryStatsRepository.fetchCountryStats(country)
}
