package ru.mrfiring.covidmvi.data.network

import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL = "https://api.coronatracker.com/"

interface CovidService {
    @GET("v3/stats/worldometer/global")
    fun getGlobalStats(): Single<GlobalStats>

}