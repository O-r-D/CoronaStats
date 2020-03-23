package com.ord.coronastats.ui.countrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.repository.CountriesStatsRepository

class CountryListViewModel(
    private val countriesStatsRepository: CountriesStatsRepository) : ViewModel() {

    fun fetchCountriesWithStats() = countriesStatsRepository.fetchCountriesStats()
}
