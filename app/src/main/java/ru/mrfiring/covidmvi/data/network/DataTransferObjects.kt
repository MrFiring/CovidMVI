package ru.mrfiring.covidmvi.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalStats(
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Double,
    val deathsPerOneMillion: Double,
    val tests: Long,
    val testsPerOneMillion: Double,
    val population: Long,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double,
    val affectedCountries: Int,
    @Json(name = "updated") val lastUpdate: Long
)
@JsonClass(generateAdapter = true)
data class ContinentCoords(
    val lat: Double,
    val long: Double
)

@JsonClass(generateAdapter = true)
data class ContinentStats(
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val todayRecovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Double,
    val deathsPerOneMillion: Double,
    val tests: Long,
    val testsPerOneMillion: Double,
    val population: Long,
    @Json(name="continent") val continentName: String,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double,
    @Json(name = "continentInfo") val continentCoords: ContinentCoords,
    @Json(name = "updated") val lastUpdate: Long,
    @Json(name = "countries") val contriesList: List<String>
)

@JsonClass(generateAdapter = true)
data class HistoricalData(
    val cases: Map<String, Long>,
    val deaths: Map<String, Long>,
    val recovered: Map<String, Long>
)

@JsonClass(generateAdapter = true)
data class CountryHistoricalStats(
    @Json(name = "country") val countryName: String,
    @Json(name = "timeline") val data: HistoricalData
)