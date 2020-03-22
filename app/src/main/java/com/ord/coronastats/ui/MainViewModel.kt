package com.ord.coronastats.ui

import androidx.lifecycle.ViewModel
import com.ord.coronastats.data.repository.WorldStatsRepository

class MainViewModel(private val worldStatsRepository: WorldStatsRepository) : ViewModel() {


    fun fetchWorldStats() = worldStatsRepository.fetchTotalCases()
}