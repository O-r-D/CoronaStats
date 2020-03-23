package com.ord.coronastats.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.data.repository.WorldStatsRepository

class MainViewModelFactory(
    private val worldStatsRepository: WorldStatsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(
        worldStatsRepository
    ) as T

}