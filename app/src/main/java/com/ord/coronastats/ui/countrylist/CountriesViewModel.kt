package com.ord.coronastats.ui.countrylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.data.repository.CountriesStatsRepository

class CountriesViewModel(
    private val countriesStatsRepository: CountriesStatsRepository) : ViewModel() {

    var countries = MutableLiveData<List<CountryStats>>()

    fun fetchCountriesWithStats() {
        countries = countriesStatsRepository.fetchCountriesStats()
    }
}
