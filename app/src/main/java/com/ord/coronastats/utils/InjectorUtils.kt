package com.ord.coronastats.utils

import com.ord.coronastats.data.repository.CountriesStatsRepository
import com.ord.coronastats.data.repository.CountryStatsRepository
import com.ord.coronastats.data.repository.WorldStatsRepository
import com.ord.coronastats.ui.country.CountryStatsViewModelFactory
import com.ord.coronastats.ui.countrylist.CountriesViewModelFactory
import com.ord.coronastats.ui.world.WorldViewModelFactory

object InjectorUtils {

    fun provideWorldViewModelFactory() =
        WorldViewModelFactory(WorldStatsRepository())

    fun provideCountryStatsViewModelFactory() =
        CountryStatsViewModelFactory(CountryStatsRepository())

    fun provideCountryListViewModelFactory() = CountriesViewModelFactory(CountriesStatsRepository())

}