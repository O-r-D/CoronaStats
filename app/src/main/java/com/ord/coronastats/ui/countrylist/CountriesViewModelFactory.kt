package com.ord.coronastats.ui.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.data.repository.CountriesStatsRepository

class CountriesViewModelFactory (
    private val countriesStatsRepository: CountriesStatsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CountriesViewModel(countriesStatsRepository) as T
}