package ru.mrfiring.covidmvi.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://disease.sh/v3/"

interface CovidService {
    @GET("/v3/covid-19/all")
    fun getGlobalStats(
        @Query("yesterday") showYesterday: Boolean = false,
        @Query("twoDaysAgo") showTwoDaysAgo: Boolean = false,
        @Query("allowNull") allowNullValues: Boolean = false
    ): Single<GlobalStats>

}