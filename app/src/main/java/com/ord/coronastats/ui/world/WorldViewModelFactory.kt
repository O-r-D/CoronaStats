package com.ord.coronastats.ui.world

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ord.coronastats.data.repository.WorldStatsRepository

class WorldViewModelFactory(
    private val worldStatsRepository: WorldStatsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = WorldViewModel(
        worldStatsRepository
    ) as T

}