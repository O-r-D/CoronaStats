package com.ord.coronastats.utils

import com.ord.coronastats.data.repository.CountriesStatsRepository
import com.ord.coronastats.data.repository.CountryStatsRepository
import com.ord.coronastats.data.repository.WorldStatsRepository
import com.ord.coronastats.ui.country.CountryStatsViewModelFactory
import com.ord.coronastats.ui.countrylist.CountryListViewModelFactory
import com.ord.coronastats.ui.main.MainViewModelFactory

object InjectorUtils {

    fun provideMainViewModelFactory() =
        MainViewModelFactory(WorldStatsRepository())

    fun provideCountryStatsViewModelFactory() =
        CountryStatsViewModelFactory(CountryStatsRepository())

    fun provideCountryListViewModelFactory() = CountryListViewModelFactory(CountriesStatsRepository())

}