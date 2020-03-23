package com.ord.coronastats.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ord.coronastats.data.model.CountryStats
import com.ord.coronastats.data.network.NovelCOVIDAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesStatsRepository {

    private val TAG = "COUNTRIES STATS"

    fun fetchCountriesStats(): MutableLiveData<List<CountryStats>> {
        val countriesStats = MutableLiveData<List<CountryStats>>()

        NovelCOVIDAPI().getCountriesStats().enqueue(object : Callback<List<CountryStats>> {
            override fun onFailure(call: Call<List<CountryStats>>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<List<CountryStats>>, response: Response<List<CountryStats>>) {
                if (response.isSuccessful)
                    countriesStats.value = response.body()
                else
                    Log.e(TAG, response.message())
            }
        })

        return countriesStats
    }


    companion object {
        @Volatile
        private var instance: CountryStatsRepository? = null

        operator fun invoke(): CountryStatsRepository = instance ?: synchronized(this) {
            instance ?: CountryStatsRepository().also {
                instance = it
            }
        }
    }
}