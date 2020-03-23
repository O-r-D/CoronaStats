package com.ord.coronastats.data.model


data class CountryStats(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Int,
    val country: String,
    val critical: Int,
    val deaths: Int,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int
) {

    override fun toString(): String = "$country:\n$cases Cases\n$deaths Dead\n$recovered Recovered\n"
}