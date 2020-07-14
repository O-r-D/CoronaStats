package com.ord.coronastats.data.model

data class WorldStats(
    val cases: Int,
    val todayCases: Int,
    val critical: Int,
    val active: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val todayRecovered: Int,
    val updated: Long
)