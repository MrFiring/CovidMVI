package ru.mrfiring.covidmvi.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://disease.sh/v3/"

enum class SortType(val str: String){
    CASES("cases"),
    TODAY_CASES("todayCases"),
    DEATHS("deaths"),
    TODAY_DEATHS("todayDeaths"),
    RECOVERED("recovered"),
    ACTIVE("active"),
    CRITICAL("critical"),
    CASES_PER_ONE_MILLION("casesPerOneMillion"),
    DEATHS_PER_ONE_MILLION("deathsPerOneMillion")
}

interface CovidService {
    @GET("covid-19/all")
    fun getGlobalStats(
        @Query("yesterday") showYesterday: Boolean = false,
        @Query("twoDaysAgo") showTwoDaysAgo: Boolean = false,
        @Query("allowNull") allowNullValues: Boolean = false
    ): Single<GlobalStats>


    @GET("covid-19/continents")
    fun getStatsByContinents(
        @Query("sort") sortBy: SortType = SortType.CASES,
        @Query("yesterday") showYesterday: Boolean = false,
        @Query("twoDaysAgo") showTwoDaysAgo: Boolean = false,
        @Query("allowNull") allowNullValues: Boolean = false
    ): Single<List<ContinentStats>>

    @GET("covid-19/historical/{country}")
    fun getHistoricalStatsByCountryName(
        @Path("country", encoded = true) countryName: String,
        @Query("lastDays") lastDays: Int = 30
    ): Single<CountryHistoricalStats>

}