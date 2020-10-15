package com.ord.coronastats.ui.world

import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.repository.WorldStatsRepository

class WorldViewModel(private val worldStatsRepository: WorldStatsRepository) : ViewModel() {


    fun fetchWorldStats() = worldStatsRepository.fetchTotalCases()
}