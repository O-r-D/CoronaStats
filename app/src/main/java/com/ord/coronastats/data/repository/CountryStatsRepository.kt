package com.ord.coronastats.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.data.network.NovelCOVIDAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryStatsRepository {

    private val TAG = "Country Stats Repo"

    fun fetchCountryStats(country: String): MutableLiveData<CountryStats> {
        val countryStats = MutableLiveData<CountryStats>()

        NovelCOVIDAPI().getCountryStats(country).enqueue(object : Callback<CountryStats> {
            override fun onFailure(call: Call<CountryStats>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<CountryStats>, response: Response<CountryStats>) {
                if (response.isSuccessful)
                    countryStats.value = response.body()
                else
                    Log.e(TAG, response.message())
            }
        })

        return countryStats
    }

    companion object {
        @Volatile
        private var instance: CountryStatsRepository? = null

        operator fun invoke() = instance ?: synchronized(this) {
            instance ?: CountryStatsRepository().also {
                instance = it
            }
        }

    }
}