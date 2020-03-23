package com.ord.coronastats.ui.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.data.repository.CountriesStatsRepository

class CountryListViewModelFactory (
    private val countriesStatsRepository: CountriesStatsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CountryListViewModel(countriesStatsRepository) as T
}