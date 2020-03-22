package com.ord.coronastats.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ord.coronastats.data.model.WorldStats
import com.ord.coronastats.data.network.NovelCOVIDAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorldStatsRepository {

    val TAG = "World Stats Repo"

    fun fetchTotalCases(): MutableLiveData<WorldStats> {
        val novelCovidClient = NovelCOVIDAPI()
        val totalCases = MutableLiveData<WorldStats>()

        Log.i(TAG, "Starting...")
        novelCovidClient.getWorldStats().enqueue(object : Callback<WorldStats> {
            override fun onFailure(call: Call<WorldStats>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<WorldStats>, response: Response<WorldStats>) {
                if (response.isSuccessful) {
                    totalCases.value = response.body()
                }
                else
                    Log.e(TAG, response.message())
            }
        })

        return totalCases
    }

    companion object{

        var instance: WorldStatsRepository? = null

        operator fun invoke() = instance ?: synchronized(this){
            instance ?: WorldStatsRepository().also {
                instance = it
            }
        }
    }
}