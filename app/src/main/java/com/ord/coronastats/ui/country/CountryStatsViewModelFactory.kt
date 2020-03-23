package com.ord.coronastats.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.data.repository.CountryStatsRepository

class CountryStatsViewModelFactory(
    private val countryStatsRepository: CountryStatsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CountryStatsViewModel(
        countryStatsRepository
    ) as T
}