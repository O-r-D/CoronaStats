package com.ord.coronastats.utils

import com.ord.coronastats.data.repository.CountryStatsRepository
import com.ord.coronastats.data.repository.WorldStatsRepository
import com.ord.coronastats.ui.CountryStatsViewModelFactory
import com.ord.coronastats.ui.MainViewModelFactory

object InjectorUtils {

    fun provideMainViewModelFactory() = MainViewModelFactory(WorldStatsRepository())

    fun provideCountryStatsViewModelFactory() = CountryStatsViewModelFactory(CountryStatsRepository())

}