package com.ord.coronastats.data.model


import com.google.gson.annotations.SerializedName

data class WorldStats(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val updated: Long
)