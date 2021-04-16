package ru.mrfiring.covidmvi.domain

data class DomainGlobalStats(
    val lastUpdate: Long = 0,
    val cases: Long = 0,
    val todayCases: Long = 0,
    val deaths: Long = 0,
    val todayDeaths: Long = 0,
    val recovered: Long = 0,
    val todayRecovered: Long = 0,
    val active: Long = 0,
    val critical: Long = 0,
    val casesPerOneMillion: Double = 0.0,
    val deathsPerOneMillion: Double = 0.0,
    val tests: Long = 0,
    val testsPerOneMillion: Double = 0.0,
    val population: Long = 0,
    val activePerOneMillion: Double = 0.0,
    val recoveredPerOneMillion: Double = 0.0,
    val criticalPerOneMillion: Double = 0.0,
    val affectedCountries: Int = 0,
)

data class DomainContinentStats(
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
    val continentCountries: List<String>
)

data class DomainCountryHistoricalStats(
    val countryName: String,
    val cases: Map<String, Long>,
    val deaths: Map<String, Long>,
    val recovered: Map<String, Long>
)

data class DomainGeneralStats(
    val name: String,
    val cases: Long,
    val todayCases: Long,
    val recovered: Long,
    val todayRecovered: Long,
    val deaths: Long,
    val todayDeaths: Long
)