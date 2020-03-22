package com.ord.coronastats.ui

import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.repository.CountryStatsRepository

class CountryStatsViewModel(
    private val countryStatsRepository: CountryStatsRepository
) : ViewModel() {

    fun fetchCountryStats(country: String) = countryStatsRepository.fetchCountryStats(country)
}
