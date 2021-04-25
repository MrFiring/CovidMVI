package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.database.DatabaseCountryHistoricalStats
import ru.mrfiring.covidmvi.data.network.CountryHistoricalStats
import ru.mrfiring.covidmvi.domain.DomainCountryHistoricalStats

fun CountryHistoricalStats.asDatabaseObjectsList(
    resolution: String
): List<DatabaseCountryHistoricalStats> {
    val list = mutableListOf<DatabaseCountryHistoricalStats>()

    data.cases.keys.forEach { date ->
        list.add(
            DatabaseCountryHistoricalStats(
                countryName = countryName,
                resolution = resolution,
                date = date,
                cases = data.cases[date] ?: 0,
                deaths = data.deaths[date] ?: 0,
                recovered = data.recovered[date] ?: 0
            )
        )

    }

    return list
}

fun List<DatabaseCountryHistoricalStats>.asDomainObject(name: String): DomainCountryHistoricalStats{
    val casesMap: MutableMap<String, Long> = mutableMapOf()
    val deathsMap: MutableMap<String, Long> = mutableMapOf()
    val recoveredMap: MutableMap<String, Long> = mutableMapOf()

    forEach {
        casesMap[it.date] = it.cases
        deathsMap[it.date] = it.deaths
        recoveredMap[it.date] = it.recovered
    }

    return DomainCountryHistoricalStats(name, casesMap, deathsMap, recoveredMap)
}