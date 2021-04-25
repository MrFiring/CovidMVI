package ru.mrfiring.covidmvi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseGlobalStats(
    @PrimaryKey(autoGenerate = false)
    val lastUpdate: Long,
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
)

@Entity
data class DatabaseContinentStats(
    @PrimaryKey(autoGenerate = false)
    val continentName: String,
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
    val lastUpdate: Long,
)

@Entity
data class DatabaseContinentCountry(
    @PrimaryKey(autoGenerate = false)
    val countryName: String,
    val continentName: String
)

@Entity(primaryKeys = ["countryName", "resolution"])
data class DatabaseCountryHistoricalStats(
    val countryName: String,
    val resolution: String,
    val date: String,
    val cases: Long,
    val deaths: Long,
    val recovered: Long
)

@Entity
data class DatabaseGlobalHistoricalStats(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resolution: String,
    val date: String,
    val cases: Long,
    val deaths: Long,
    val recovered: Long
)
