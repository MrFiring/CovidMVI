package ru.mrfiring.covidmvi.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mrfiring.covidmvi.domain.SortType

const val BASE_URL = "https://disease.sh/v3/"

interface CovidService {
    @GET("covid-19/all")
    fun getGlobalStats(
        @Query("yesterday") showYesterday: Boolean = false,
        @Query("twoDaysAgo") showTwoDaysAgo: Boolean = false,
        @Query("allowNull") allowNullValues: Boolean = false
    ): Single<GlobalStats>


    @GET("covid-19/continents")
    fun getStatsByContinents(
        @Query("sort") sortBy: String,
        @Query("yesterday") showYesterday: Boolean = false,
        @Query("twoDaysAgo") showTwoDaysAgo: Boolean = false,
        @Query("allowNull") allowNullValues: Boolean = false
    ): Single<List<ContinentStats>>

    @GET("covid-19/historical/{country}")
    fun getHistoricalStatsByCountryName(
        @Path("country", encoded = true) countryName: String,
        @Query("lastDays") lastDays: String
    ): Single<CountryHistoricalStats>

    @GET("covid-19/historical/all")
    fun getGlobalHistoricalStats(
        @Query("lastDays") lastDays: String
    ): Single<HistoricalData>

}