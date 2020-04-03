package com.ord.coronastats.data.network

import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.data.model.WorldStats
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NovelCOVIDAPI {

    @GET("all")
    fun getWorldStats(): Call<WorldStats>

    @GET("countries/{country}")
    fun getCountryStats(@Path("country") country: String): Call<CountryStats>

    @GET("countries?sort=cases")
    fun getCountriesStats(): Call<List<CountryStats>>

    companion object{
        operator fun invoke(): NovelCOVIDAPI{
            return Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NovelCOVIDAPI::class.java)
        }
    }
}